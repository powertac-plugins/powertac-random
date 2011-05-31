class PowertacRandomGrailsPlugin {
  // the plugin version
  def version = "0.2"
  // the version or versions of Grails the plugin is designed for
  def grailsVersion = "1.3.7 > *"
  // the other plugins this plugin depends on
  def dependsOn = [:]
  // resources that are excluded from plugin packaging
  def pluginExcludes = [
      "grails-app/views/error.gsp"
  ]

  def author = "Carsten Block"
  def authorEmail = "powertac@carstenblock.org"
  def title = "Provides random seed data for powertac competition."
  def description = '''\\
This plugin provides SeedType random seed data for powertac competition classes relying on Java's SecureRandom algorithm for generating random data. The pluigin exposes a RandomSeedService that posesses a 'replayCompetitionId' property. While this property is null. New random data is generated upon each request and each generated seed value is stored in the database. If replayCompetitionId is set, the RandSeedService tries to serve previously stored seed values for the given competition and only generates (and stores) new random values if database lookup fails.
'''
  // URL to the plugin's documentation
  def documentation = "http://powertac.org/plugin/powertac-SeedType"

  def doWithWebDescriptor = { xml ->
    // TODO Implement additions to web.xml (optional), this event occurs before
  }

  def doWithSpring = {
    // TODO Implement runtime spring config (optional)
  }

  def doWithDynamicMethods = { ctx ->
    // TODO Implement registering dynamic methods to classes (optional)
  }

  def doWithApplicationContext = { applicationContext ->
    // TODO Implement post initialization spring config (optional)
  }

  def onChange = { event ->
    // TODO Implement code that is executed when any artefact that this plugin is
    // watching is modified and reloaded. The event contains: event.source,
    // event.application, event.manager, event.ctx, and event.plugin.
  }

  def onConfigChange = { event ->
    // TODO Implement code that is executed when the project configuration changes.
    // The event is the same as for 'onChange'.
  }
}
