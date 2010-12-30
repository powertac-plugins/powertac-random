package org.powertac.random

import java.security.SecureRandom

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
    def randomValue = random."next${seedType == 'Integer'? 'Int' : seedType}"()
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

  def nextBoolean (String competitionId, String requesterClass, String requesterId, String purpose) {
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
