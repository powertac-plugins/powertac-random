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

/**
 * RandomSeed is used to store generated random seed in the database in
 * order to be able to "replay" PowerTAC competitions later on with
 * exactly the same random seed settings as originally used.
 *
 * @author Carsten Block
 * @version 1.0 - January 01, 2011
 */
class RandomSeed {
  String id = UUID.randomUUID();
  String requesterClass
  String requesterId
  String purpose = 'any'
  Long value

  static constraints = {
    requesterClass (blank: false)
    requesterId(nullable: true)
    purpose (nullable: false)
    value (nullable: false)
  }

  static mapping = {
    cache true
    id (generator: 'assigned')
    requesterClass (index: 'random_seed_idx')
    requesterId (index: 'random_seed_idx')
    purpose (index: 'random_seed_idx')
  }
}
