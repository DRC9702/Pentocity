package pentos.g10;

import java.util.HashSet;
import java.util.Set;

import pentos.sim.Building;
import pentos.sim.Cell;
import pentos.sim.Land;

public class FactoryPlanner implements Planner{

	@Override
	public Action makeAPlan(Player player, Building request, Land land) {
		double score = -100.0;
		Action toTake = new Action();

		// try all cells as start point
		for (Cell thisStart : player.factoryStart) {
			/*
			 * Because the factory grows from the bottom right corner, the
			 * starting point to be used to build it should be the bottom right
			 * corner.
			 */
			Building[] rotations = request.rotations();
			int len = rotations.length;
			// System.out.println(len+" rotations available.");
			for (int i = 0; i < len; i++) {
				Building b = rotations[i];
				/*
				 * 4 ways to interpret the starting point: 1. As the top left
				 * corner 2. As the top right corner 3. As the bottom left
				 * corner 4. As the bottom right corner
				 * 
				 * But since land.buildable() only interprets the point as top
				 * left, we need to do the shifting ourselves
				 * 
				 */
				Cell fromTopLeft = thisStart;
				Cell fromTopRight = ToolBox.shiftFromTopRight(b, thisStart);
				Cell fromBottomLeft = ToolBox.shiftFromBottomLeft(b, thisStart);
				Cell fromBottomRight = ToolBox.shiftFromBottomRight(b, thisStart);
				Set<Cell> startPoints = new HashSet<>();
				if (fromTopLeft != null)
					startPoints.add(fromTopLeft);
				if (fromTopRight != null)
					startPoints.add(fromTopRight);
				if (fromBottomLeft != null)
					startPoints.add(fromBottomLeft);
				if (fromBottomRight != null)
					startPoints.add(fromBottomRight);

				for (Cell c : startPoints) {
					/* If this move is valid, evaluate it. */
					boolean canBuild = land.buildable(b, c);
					if (canBuild) {
						Action toCheck = new Action(request, c, i);

						/*
						 * Include the road cells
						 */
						Set<Cell> roads = RoadFinder.findRoad(player, toCheck, land);
						if (roads == null) {
							System.out.println("Cannot plan roads. This building will not work.");
							continue;
						}
						toCheck.setRoadCells(roads);

						/*
						 * The score of a solution is, for now, decided by how
						 * close it can be packed with the existing cluster.
						 */
						// thisScore=calculateScore(residenceStart,occupyThen,availThen,roads);
						double thisScore = PlanEvaluator.evaluatePlan(player, toCheck, land);
						if (thisScore > score) {
							score = thisScore;
							toTake = toCheck;
						}
					}

				} // looped all possible shifts of start points
			} // looped all rotations
		} // looped all candidate positions
		System.out.println("The optimal solution so far has score:" + score);

		/* If the score is not 0, perform it. */
		if (score == -100.0) {
			System.out.println("No solution found.");
		}
		return toTake;
	}

}
