/*
 * Copyright 2009-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an
 * "AS IS" BASIS,  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package org.powertac.random

import java.security.SecureRandom

/**
 * RandomSeedService provides a thin wrapper around {@link java.security.SecureRandom}
 * With {@code replayCompetitionId} set to {@code null} it generates random numbers on
 * demand and stores them in the database, also persisting the class, id, and purpose
 * of the entity that invoked the service.
 *
 * With {@code replayCompetitionId} set to a valid (=existing) competitionId, the service
 * tries to serve the originally generated random numbers using stored random seeds from the
 * database. Only if db lookup fails, a new random number is generated (and persisted to the
 * database).
 *
 * @author Carsten Block
 * @version 1.0 - January 01, 2011
 */
class RandomSeedService {

  static transactional = true
  static def random = new SecureRandom()

  String replayCompetitionId

  private def nextRandom = {competitionId, requesterClass, requesterId, purpose, seedType ->
    assert (competitionId)
    assert (requesterClass)
    assert (purpose)
    assert (seedType)

    RandomSeed randomSeed = RandomSeed.withCriteria(uniqueResult: true) {
      eq('competitionId', competitionId)
      eq('requesterClass', requesterClass)
      if (requesterId) {
        eq('requesterId', requesterId)
      } else {
        isNull('requesterId')
      }
      eq('purpose', purpose)
      eq('seedType', seedType)
      maxResults(1)
      order('id', 'desc')
    }
    def randomValue = random."next${seedType == 'Integer' ? 'Int' : seedType}"()
    if (replayCompetitionId) {
      if (!randomSeed) {
        if (log.isErrorEnabled()) log.error("PowerTAC random service in replayCompetitionId mode but no seed found in db for requesterId: '$requesterId', purpose: '$purpose', seedType: '$seedType'. Generating new seed...")
        randomSeed = new RandomSeed(competitionId: competitionId, requesterClass: requesterClass, requesterId: requesterId, purpose: purpose, seedType: seedType, value: randomValue.toString())
        assert (randomSeed.validate() && randomSeed.save())
      } else {
        randomValue = randomSeed.value."to${seedType == 'Gaussian' ? 'Double' : seedType}"()
      }
    } else {
      if (!randomSeed) {
        randomSeed = new RandomSeed(competitionId: competitionId, requesterClass: requesterClass, requesterId: requesterId, purpose: purpose, seedType: seedType, value: randomValue.toString())
        assert (randomSeed.validate() && randomSeed.save())
      } else {
        if (log.isInfoEnabled()) log.info("Overwriting random seed '${randomSeed.value}' for requesterId: '$requesterId', purpose: '$purpose', seedType: '${seedType}' with '${randomValue}'")
        randomSeed.value = randomValue.toString()
        assert (randomSeed.validate() && randomSeed.save())
      }
    }
    return randomValue
  }

  def nextBoolean(String competitionId, String requesterClass, String requesterId, String purpose) {
    return nextRandom(competitionId, requesterClass, requesterId, purpose, 'Boolean')
  }

  def nextInt(String competitionId, String requesterClass, String requesterId, String purpose) {
    return nextRandom(competitionId, requesterClass, requesterId, purpose, 'Integer')
  }

  def nextFloat(String competitionId, String requesterClass, String requesterId, String purpose) {
    return nextRandom(competitionId, requesterClass, requesterId, purpose, 'Float')
  }

  def nextDouble(String competitionId, String requesterClass, String requesterId, String purpose) {
    return nextRandom(competitionId, requesterClass, requesterId, purpose, 'Double')
  }

  def nextLong(String competitionId, String requesterClass, String requesterId, String purpose) {
    return nextRandom(competitionId, requesterClass, requesterId, purpose, 'Long')
  }

  def nextGaussian(String competitionId, String requesterClass, String requesterId, String purpose) {
    return nextRandom(competitionId, requesterClass, requesterId, purpose, 'Gaussian')
  }
}
