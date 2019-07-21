import java.io.File

object CreateClassTemplateOnce extends App {
  lazy val defaulktBlackList = Set("AlwaysDefined", "SlowKeyboard", "LinePlotter", "DifferentPokerHands", "BlockEnemy", "DominoesGame", "SantaClaus", "FindTriangle", "CakesEqualization", "WorldPeace", "BoxFilling", "FastGossip", "ExperimentalAnalyzer", "KMonotonic", "AbsSequence", "CheatABit", "CompressionText", "ContiguousCache", "Genetics", "CandyGame", "Hex", "StackMachine", "Nisoku", "Find3Cheaters", "Wardrobe", "CaseysArt", "ClosestPoints", "PackageShipping", "RiverHill", "Polyline", "AntiChess", "MagicNaming", "BioScore", "CCWTurning", "TheNewHouseDivOne", "CounterfeitCoin", "Triptych", "KiloManX", "DeviceProgramming", "ParkingLot", "ContestCoordinator", "Spanning", "Pitches", "BinaryMatrix", "RingCount", "Horoscope", "OptimalGroupMovement", "PieSharing", "ComplementMachine2D", "HanoiDistance", "TextStatistics", "ColorfulBalls", "RoundTable", "PartialSeries", "ReliefMeasuring", "CountryWar", "MonotoneSequence", "SlimeXResidentSlime", "TeleportAndLoader", "CannonBalls", "TwoKings", "Police", "Switching", "Springs", "JohnnysCannon", "MetroNetwork", "Dating", "DukeOnLargeChessBoard", "MislabeledWeights", "PaperThickness", "TablePartitions", "ThreeMines", "CreatureTraining", "BadNeighbors", "MostCommonLetters", "Justify", "BallGift", "Crossings", "ConductorWalk", "NimForK", "Assemble", "ShortTaps", "TVTower", "GroupWork", "ChartError", "Predicting", "BiggestRectangleHard", "ConnectTheCities", "BalanceScale", "BusinessPlan", "ToolingUp", "Recipe", "ConnectingPoints", "TestingCar", "TrophyShelf", "LongBlob", "NegativePhotoresist", "BasketballStandings", "OrderFood", "SelfCatalogue", "Equity", "NumberOfDivisors", "WatchTower", "SplitAndMergeGame", "QuipuReader", "ConvexHull", "CoinWeight", "Musical", "CakeDivision", "CirclesOfDestruction", "SpiralConstruction", "StarAdventure", "BinaryQuipu", "HillWalker", "Lister", "RPGRobot", "PlayerExtraction", "ClockManagement", "Overhang", "DNAString", "LateForConcert", "PubTrivia", "PaintingBoards", "CheatABit", "LakeDepth", "RestoringPolygon", "InscribedTriangles", "Disaster", "MaximizeSquares", "MailArchiving", "TheBoredJohn", "StripePainter", "ArithSeq", "ContractWork", "Alignment", "ContextFreeGrammars", "LogCabin", "SeparateConnections", "HeightRound", "RearrangeFurniture", "TheSwap", "LampsGrid", "SalesPromotion", "PolylineUnion", "TrainRobber", "NCool", "HiddenTriangles", "TVWatching", "SuperString", "CommonSubsequence", "CodeSet", "PokerDeck", "ComboBoxKeystrokes", "MoneyRun", "StackingBoxes", "CheapestFlights", "ModelRailroad", "ParallelProgramming", "Diamonds", "PermutationValues", "ChuckContest", "StickedWords", "CarolsSinging", "PlaneDivision", "HexagonIntersections", "CheckerPolygon", "HeartsGame", "ValetParking", "WordPuzzle", "AutoAdjust", "DiscCover", "NumberChanger", "ColoredBricks", "WalkingDistance", "Books", "ChutesAndLadders", "PrimeStatistics", "WorkersOnPlane", "BerryPacker", "TheEasyChase", "GoldMine", "Distincter", "TheHomework", "Driving")
  run("BobTrouble")

  def run(targetClassName: String, blackList:Set[String] = defaulktBlackList) : Unit={
    if (blackList.contains(targetClassName)) (println("black listed class: "+ targetClassName))
    else {
      val inputDir = "/Users/mas.kimura/topcoder/testcases2/formatted/input"
      val inputFileList = new File(inputDir).listFiles
      inputFileList.find(file => file.getName.contains(s"-${targetClassName}")) match {
        case Some(i) => CreateClassTemplate.run(targetClassName)
        case None => println("the class was not found: "+targetClassName)
      }
    }
  }
}

