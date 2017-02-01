import akka.actor._
import akka.persistence._

class Counter extends PersistentActor with ActorLogging {

    import Counter._

    override def persistenceId: String = "counter"
    var state: State = new State( 0 )

    override def receiveRecover: Receive = 
    {
        case RecoveryCompleted => {
            println( "Recovery completed." )
        }
        case SnapshotOffer( _, snapshot: State ) => {
            state = snapshot
        }
        case op: Operation => {
            updateState( op )
        }
    }

    override def receiveCommand: Receive = 
    {
        case op: Operation => {
            println( s"Counter receive ${op}" )
            persist( op ) 
            {
                op => updateState( op )
            }
        }
    
        case "print" => {
            println(s"counter's current state: ${state}")
        }

        case SaveSnapshotFailure( _, reason ) => {
            println( s"save snapshot failed, reason: ${reason}" )
        }
    
        case SaveSnapshotSuccess( _ ) => {
            println(s"snapshot saved")
        }
    }

    def updateState( op: Operation ): Unit = {
        op match {
            case Increment( n ) => {
                state = state.inc( n )
                takeSnapshot
            }

            case Decrement( n ) => {
                state = state.dec( n )
                takeSnapshot
            }
        }
    }

    def takeSnapshot: Unit = 
    {
        saveSnapshot( state )
    }
}


object Counter 
{
    sealed trait Operation 
    {
        val count: Int
    }

    case class Increment(override val count: Int) extends Operation

    case class Decrement(override val count: Int) extends Operation

    final case class State( n: Int ) 
    {
        def inc(x: Int) = 
        {
            State( n + x )
        }
    
        def dec( x: Int ) = 
        {
            State( n - x )
        }
    }
}

object PersistenceDemo
{
    import akka.util.Timeout
    import scala.concurrent.duration._
    import akka.pattern.ask
    import akka.dispatch.ExecutionContexts._
    implicit val ec = global

    def main(args: Array[String]) 
    {
        import Counter._

        val system = ActorSystem("persistent-actors")
        val counter = system.actorOf(Props[Counter], "counter")

        counter ! Increment(3)
        counter ! Increment(5)
        counter ! Decrement(3)
        counter ! "print"

        Thread.sleep(10000)
        system.terminate()
    }
}

