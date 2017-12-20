object Catan extends App {

  sealed trait ResourceTile {
    def roll: Int
    def hasRobber: Boolean
  }
  object ResourceTile {
    case class Brick(roll: Int, hasRobber: Boolean) extends ResourceTile
    case class Lumber(roll: Int, hasRobber: Boolean) extends ResourceTile
    case class Wool(roll: Int, hasRobber: Boolean) extends ResourceTile
    case class Grain(roll: Int, hasRobber: Boolean) extends ResourceTile
    case class Ore(roll: Int, hasRobber: Boolean) extends ResourceTile
  }

  sealed trait ResourceCard
  object ResourceCard {
    case object Brick extends ResourceCard
    case object Lumber extends ResourceCard
    case object Wool extends ResourceCard
    case object Grain extends ResourceCard
    case object Ore extends ResourceCard
  }

  sealed trait Purchasable {
    def cost: Map[ResourceCard, Int]
  }

  case object Road extends Purchasable {
    def cost = Map(ResourceCard.Brick -> 1, ResourceCard.Lumber -> 1)
  }

  sealed trait Building extends Purchasable
  object Building {
    case object Settlement extends Building {
      def cost = Map(ResourceCard.Brick -> 1, ResourceCard.Lumber -> 1, ResourceCard.Grain -> 1, ResourceCard.Wool -> 1)
    }
    case object City extends Building {
      def cost = Map(ResourceCard.Grain -> 2, ResourceCard.Ore -> 3)
    }
  }

  sealed trait DevelopmentCard extends Purchasable {
    def cost = Map(ResourceCard.Wool -> 1, ResourceCard.Grain -> 1, ResourceCard.Ore -> 1)
  }
  object DevelopmentCard {
    case object VictoryPoint extends DevelopmentCard
    case object Soldier extends DevelopmentCard
    case object RoadBuilding extends DevelopmentCard
    case object YearOfPlenty extends DevelopmentCard
  }

  case class Node(building: Option[Building], adjacentResourceTiles: Seq[ResourceTile])
  case class Edge(a: Node, b: Node, road: Option[Player])
  case class GameState(graph: (Set[Node], Set[Edge]), players: Set[Player])
  case class Player(resourceCards: Set[ResourceCard], purchasables: Set[Purchasable], victoryPoints: Int)

  type Transaction = (
    (Player, Map[ResourceCard, Int]),
    (Player, Map[ResourceCard, Int])
  )

  // an Action is anything that changes GameState
  sealed trait Action
  object Action {
    case class DrawDevelopmentCard(player: Player) extends Action
    case class PlayDevelopmentCard(player: Player, card: DevelopmentCard) extends Action
    case class Purchase(player: Player, purchasable: Purchasable) extends Action
    case class Trade(transaction: Transaction) extends Action
    case class Roll() extends Action
  }

  /*
  TODO: Initialize the world here:
   */
  //  def initializeGameState: GameState = { ... }

  /*
  TODO: Execute a move here:
   */
  //  def move(action: Action, g: GameState): GameState = { ... }

  /*
  TODO: Implement some sort of game loop, which houses player moves
   */

}
