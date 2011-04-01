import sbt._
import org.scalatools.testing.Event
import org.scalatools.testing.{Result => TestingResult}

class ScoreBoardUpdater(publisher: ScoreBoardPublish) extends TestsListener {

  var failed = 0
  var passed = 0
  var skipped = 0

  /** called once, at beginning. */
  def doInit {
    failed = 0
    passed = 0
    skipped = 0
  }

  /** called for each test method or equivalent */
  def testEvent(event: TestEvent) {
    event match {
      case e =>
        val detail = e.detail

        for (i <- detail) {
          i match {
            case te: Event =>
              te.result match {
                case su: TestingResult if su == TestingResult.Success => passed += 1
                case fa: TestingResult if fa == TestingResult.Failure => failed += 1
                case sk: TestingResult if sk == TestingResult.Skipped => skipped += 1
              }
          }
        }
    }
  }

  /** called once, at end. */
  def doComplete(finalResult: Result.Value) {
    publisher.publishScore(passed, failed, skipped)
  }

  /** WE DON'T NEED THESE */
  /** called for each class or equivalent grouping */
  def startGroup(name: String) {}
  /** called if there was an error during test */
  def endGroup(name: String, t: Throwable) {}
  /** called if test completed */
  def endGroup(name: String, result: Result.Value) {}

}

trait ScoreBoardPublish extends BasicScalaProject {
    def publishScore(passed: Int, failed: Int, skipped: Int)
}