package org.powertac.random

class RandomSeed {
  String id = UUID.randomUUID();
  String competitionId
  String requesterClass
  String requesterId
  String purpose = 'any'
  String seedType
  String value

  static constraints = {
    competitionId (blank: false)
    requesterClass (blank: false)
    requesterId(nullable: true)
    purpose (nullable: false)
    seedType(nullable: false)
    value (blank: false)
  }

  static mapping = {
    cache true
    id (generator: 'assigned')
    competitionId (index: 'random_seed_idx')
    requesterClass (index: 'random_seed_idx')
    requesterId (index: 'random_seed_idx')
    purpose (index: 'random_seed_idx')
    seedType (index: 'random_seed_idx')
  }
}
