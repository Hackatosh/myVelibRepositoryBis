package myVelibCore.planningPolicyPackage;

import myVelibCore.exceptions.BadInstantiationException;

public class PolicyChoice {
	public static PlanningPolicy getPolicy(String policy) throws BadInstantiationException {
		if(policy.equalsIgnoreCase("Avoid_Plus_Stations")) {return new AvoidPlusStation();}
		if(policy.equalsIgnoreCase("Prefer_Plus_Stations")) {return new PreferPlusStation();}
		if(policy.equalsIgnoreCase("Uniformity_Policy")) {return new UniformityPolicy();}
		if(policy.equalsIgnoreCase("Shortest_Path")) {return new ShortestPath();}
		if(policy.equalsIgnoreCase("Fastest_Path")) {return new FastestPath();}
		else{throw new BadInstantiationException(policy, "Policy");}
	}
}
