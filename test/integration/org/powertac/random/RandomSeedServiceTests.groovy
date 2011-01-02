package org.powertac.random

class RandomSeedServiceTests extends GroovyTestCase {

  def randomSeedService

  String competitionId
  String requesterClass
  String requesterId
  String purpose
  String anotherPurpose

  protected void setUp() {
    super.setUp()
    competitionId = 'testCompetition'
    requesterClass = 'testClass'
    requesterId = 'requesterId'
    purpose = 'somePurpose'
    anotherPurpose = 'anotherPurpose'
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testRandomSeedServiceNotNull() {
    assertNotNull(randomSeedService)
  }

  void testNullValues() {
    assertNotNull(randomSeedService.nextDouble('competitionId', 'requesterClass', 'requesterId', 'purpose'))
    shouldFail {randomSeedService.nextDouble(null, 'requesterClass', 'requesterId', 'purpose')}
    shouldFail {randomSeedService.nextDouble('competitionId', null, 'requesterId', 'purpose')}
    shouldFail {randomSeedService.nextDouble('competitionId', 'requesterClass', 'requesterId', null)}
  }


  void testRandomSeedServiceWithRequesterIdNull() {
    randomSeedService.replayCompetitionId = null
    assertNull(randomSeedService.replayCompetitionId)
    double instance1 = randomSeedService.nextDouble(competitionId, requesterClass, null, purpose)
    assertNotNull(instance1)

    double instance2 = randomSeedService.nextDouble(competitionId, requesterClass, null, purpose)
    assertNotNull(instance2)
    assertFalse(instance1.equals(instance2))

    randomSeedService.replayCompetitionId = competitionId

    assertEquals(competitionId, randomSeedService.getReplayCompetitionId())

    double instance3 = randomSeedService.nextDouble(competitionId, requesterClass, null, purpose)
    assertEquals(instance2, instance3)

    double instance4 = randomSeedService.nextDouble(competitionId, requesterClass, null, anotherPurpose)
    assertFalse(instance3.equals(instance4))

    double instance5 = randomSeedService.nextDouble(competitionId, requesterClass, null, anotherPurpose)
    assertEquals(instance4, instance5)
  }

  void testRandomSeedServiceReplayDouble() {

    randomSeedService.replayCompetitionId = null
    assertNull(randomSeedService.replayCompetitionId)
    double instance1 = randomSeedService.nextDouble(competitionId, requesterClass, requesterId, purpose)
    assertNotNull(instance1)

    double instance2 = randomSeedService.nextDouble(competitionId, requesterClass, requesterId, purpose)
    assertNotNull(instance2)
    assertFalse(instance1.equals(instance2))

    randomSeedService.replayCompetitionId = competitionId

    assertEquals(competitionId, randomSeedService.getReplayCompetitionId())

    double instance3 = randomSeedService.nextDouble(competitionId, requesterClass, requesterId, purpose)
    assertEquals(instance2, instance3)

    double instance4 = randomSeedService.nextDouble(competitionId, requesterClass, requesterId, anotherPurpose)
    assertFalse(instance3.equals(instance4))

    double instance5 = randomSeedService.nextDouble(competitionId, requesterClass, requesterId, anotherPurpose)
    assertEquals(instance4, instance5)
  }

  void testRandomSeedServiceReplayGaussian() {
    randomSeedService.replayCompetitionId = null
    assertNull(randomSeedService.replayCompetitionId)
    double instance1 = randomSeedService.nextGaussian(competitionId, requesterClass, requesterId, purpose)
    assertNotNull(instance1)

    double instance2 = randomSeedService.nextGaussian(competitionId, requesterClass, requesterId, purpose)
    assertNotNull(instance2)
    assertFalse(instance1.equals(instance2))

    randomSeedService.replayCompetitionId = competitionId

    assertEquals(competitionId, randomSeedService.getReplayCompetitionId())

    double instance3 = randomSeedService.nextGaussian(competitionId, requesterClass, requesterId, purpose)
    assertEquals(instance2, instance3)

    double instance4 = randomSeedService.nextGaussian(competitionId, requesterClass, requesterId, anotherPurpose)
    assertFalse(instance3.equals(instance4))

    double instance5 = randomSeedService.nextGaussian(competitionId, requesterClass, requesterId, anotherPurpose)
    assertEquals(instance4, instance5)
  }


  void testRandomSeedServiceReplayFloat() {

    randomSeedService.replayCompetitionId = null
    assertNull(randomSeedService.replayCompetitionId)
    float instance1 = randomSeedService.nextFloat(competitionId, requesterClass, requesterId, purpose)
    assertNotNull(instance1)

    float instance2 = randomSeedService.nextFloat(competitionId, requesterClass, requesterId, purpose)
    assertNotNull(instance2)
    assertFalse(instance1.equals(instance2))

    randomSeedService.replayCompetitionId = competitionId

    assertEquals(competitionId, randomSeedService.getReplayCompetitionId())

    float instance3 = randomSeedService.nextFloat(competitionId, requesterClass, requesterId, purpose)
    assertEquals(instance2, instance3)

    float instance4 = randomSeedService.nextFloat(competitionId, requesterClass, requesterId, anotherPurpose)
    assertFalse(instance3.equals(instance4))

    float instance5 = randomSeedService.nextFloat(competitionId, requesterClass, requesterId, anotherPurpose)
    assertEquals(instance4, instance5)
  }

  void testRandomSeedServiceReplayInteger() {

    randomSeedService.replayCompetitionId = null
    assertNull(randomSeedService.replayCompetitionId)
    int instance1 = randomSeedService.nextInt(competitionId, requesterClass, requesterId, purpose)
    assertNotNull(instance1)

    int instance2 = randomSeedService.nextInt(competitionId, requesterClass, requesterId, purpose)
    assertNotNull(instance2)
    assertFalse(instance1.equals(instance2))

    randomSeedService.replayCompetitionId = competitionId

    assertEquals(competitionId, randomSeedService.getReplayCompetitionId())

    int instance3 = randomSeedService.nextInt(competitionId, requesterClass, requesterId, purpose)
    assertEquals(instance2, instance3)

    int instance4 = randomSeedService.nextInt(competitionId, requesterClass, requesterId, anotherPurpose)
    assertFalse(instance3.equals(instance4))

    int instance5 = randomSeedService.nextInt(competitionId, requesterClass, requesterId, anotherPurpose)
    assertEquals(instance4, instance5)
  }

  void testRandomSeedServiceReplayLong() {

    randomSeedService.replayCompetitionId = null
    assertNull(randomSeedService.replayCompetitionId)
    long instance1 = randomSeedService.nextLong(competitionId, requesterClass, requesterId, purpose)
    assertNotNull(instance1)

    long instance2 = randomSeedService.nextLong(competitionId, requesterClass, requesterId, purpose)
    assertNotNull(instance2)
    assertFalse(instance1.equals(instance2))

    randomSeedService.replayCompetitionId = competitionId

    assertEquals(competitionId, randomSeedService.getReplayCompetitionId())

    long instance3 = randomSeedService.nextLong(competitionId, requesterClass, requesterId, purpose)
    assertEquals(instance2, instance3)

    long instance4 = randomSeedService.nextLong(competitionId, requesterClass, requesterId, anotherPurpose)
    assertFalse(instance3.equals(instance4))

    long instance5 = randomSeedService.nextLong(competitionId, requesterClass, requesterId, anotherPurpose)
    assertEquals(instance4, instance5)
  }

  void testRandomSeedServiceBoolean() {

    randomSeedService.replayCompetitionId = null
    assertNull(randomSeedService.replayCompetitionId)
    boolean instance1 = randomSeedService.nextBoolean(competitionId, requesterClass, requesterId, purpose)
    assertNotNull(instance1)

    boolean instance2 = randomSeedService.nextBoolean(competitionId, requesterClass, null, purpose)
    assertNotNull(instance2)

    randomSeedService.replayCompetitionId = competitionId

    boolean instance3 = randomSeedService.nextBoolean(competitionId, requesterClass, null, purpose)
    boolean instance4 = randomSeedService.nextBoolean(competitionId, requesterClass, null, purpose)
    boolean instance5 = randomSeedService.nextBoolean(competitionId, requesterClass, null, purpose)
    boolean instance6 = randomSeedService.nextBoolean(competitionId, requesterClass, null, purpose)
    boolean instance7 = randomSeedService.nextBoolean(competitionId, requesterClass, null, purpose)
    assertTrue (instance3 == instance4)
    assertTrue (instance4 == instance5)
    assertTrue (instance5 == instance6)
    assertTrue (instance6 == instance7)
  }


}
