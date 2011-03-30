import sbt._

class ScoreBoardUpdater extends TestsListener {

  //val actor = Actor.remote.actorFor("scoreKeeper", "localhost", 9999)
  var failed = 0
  var passed = 0

  /** called for each test method or equivalent */
  def testEvent(event: TestEvent) {
    if (event.result == Result.Passed) passed += 1
    if (event.result == Result.Failed) failed += 1
  }

  /** called once, at beginning. */
  def doInit {}
  /** called once, at end. */
  def doComplete(finalResult: Result.Value) {}
  /** called for each class or equivalent grouping */
  def startGroup(name: String) {}
  /** called if there was an error during test */
  def endGroup(name: String, t: Throwable) {}
  /** called if test completed */
  def endGroup(name: String, result: Result.Value) {}

}