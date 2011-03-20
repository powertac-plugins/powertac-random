package org.powertac.random

class RandomSeedServiceTests extends GroovyTestCase {

  def randomSeedService

  String requesterClass
  String requesterId
  String purpose
  String anotherPurpose

  protected void setUp() {
    super.setUp()
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
    assertNotNull(randomSeedService.nextSeed('requesterClass', 'requesterId', 'purpose'))
    shouldFail {randomSeedService.nextSeed(null, 'requesterId', 'purpose')}
    shouldFail {randomSeedService.nextSeed('requesterClass', 'requesterId', null)}
  }


  void testRandomSeedServiceWithRequesterIdNull() {
    randomSeedService.replay = false
    assertFalse(randomSeedService.replay)
    long instance1 = randomSeedService.nextSeed(requesterClass, null, purpose)
    assertNotNull(instance1)

    long instance2 = randomSeedService.nextSeed(requesterClass, null, purpose)
    assertNotNull(instance2)
    assertFalse(instance1.equals(instance2))

    randomSeedService.replay = true

    assertTrue(randomSeedService.getReplay())

    double instance3 = randomSeedService.nextSeed(requesterClass, null, purpose)
    assertEquals(instance2, instance3)

    double instance4 = randomSeedService.nextSeed(requesterClass, null, anotherPurpose)
    assertFalse(instance3 == instance4)

    double instance5 = randomSeedService.nextSeed(requesterClass, null, anotherPurpose)
    assertEquals(instance4, instance5)
  }

  void testRandomSeedServiceReplay() {

    randomSeedService.replay = false
    assertFalse(randomSeedService.replay)
    long instance1 = randomSeedService.nextSeed(requesterClass, requesterId, purpose)
    assertNotNull(instance1)

    long instance2 = randomSeedService.nextSeed(requesterClass, requesterId, purpose)
    assertNotNull(instance2)
    assertFalse(instance1.equals(instance2))

    randomSeedService.replay = true

    assertTrue(randomSeedService.getReplay())

    long instance3 = randomSeedService.nextSeed(requesterClass, requesterId, purpose)
    assertEquals(instance2, instance3)

    long instance4 = randomSeedService.nextSeed(requesterClass, requesterId, anotherPurpose)
    assertFalse(instance3 == instance4)

    long instance5 = randomSeedService.nextSeed(requesterClass, requesterId, anotherPurpose)
    assertEquals(instance4, instance5)
  }
}
