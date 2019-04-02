import java.util.LinkedList;

public class TP1_1 {

	

	public static void main(int argc,char[][] argv){
		
	}
	

    public static void main(String[] args){
        Map<String, String> matches = match(guys, guyPrefers, girlPrefers);
        for(Map.Entry<String, String> couple:matches.entrySet()){
            System.out.println(
                    couple.getKey() + " is engaged to " + couple.getValue());
        }
        if(checkMatches(guys, girls, matches, guyPrefers, girlPrefers)){
            System.out.println("Marriages are stable");
        }else{
            System.out.println("Marriages are unstable");
        }
        String tmp = matches.get(girls.get(0));
        matches.put(girls.get(0), matches.get(girls.get(1)));
        matches.put(girls.get(1), tmp);
        System.out.println(
                girls.get(0) +" and " + girls.get(1) + " have switched partners");
        if(checkMatches(guys, girls, matches, guyPrefers, girlPrefers)){
            System.out.println("Marriages are stable");
        }else{
            System.out.println("Marriages are unstable");
        }
    }
 
    private static Map<String, String> match(List<String> guys,
            Map<String, List<String>> guyPrefers,
            Map<String, List<String>> girlPrefers){
        Map<String, String> engagedTo = new TreeMap<String, String>();
        List<String> freeGuys = new LinkedList<String>();
        freeGuys.addAll(guys);
        while(!freeGuys.isEmpty()){
            String thisGuy = freeGuys.remove(0); //get a load of THIS guy
            List<String> thisGuyPrefers = guyPrefers.get(thisGuy);
            for(String girl:thisGuyPrefers){
                if(engagedTo.get(girl) == null){//girl is free
                    engagedTo.put(girl, thisGuy); //awww
                    break;
                }else{
                    String otherGuy = engagedTo.get(girl);
                    List<String> thisGirlPrefers = girlPrefers.get(girl);
                    if(thisGirlPrefers.indexOf(thisGuy) <
                            thisGirlPrefers.indexOf(otherGuy)){
                        //this girl prefers this guy to the guy she's engaged to
                        engagedTo.put(girl, thisGuy);
                        freeGuys.add(otherGuy);
                        break;
                    }//else no change...keep looking for this guy
                }
            }
        }
        return engagedTo;
    }
 
    private static boolean checkMatches(List<String> guys, List<String> girls,
            Map<String, String> matches, Map<String, List<String>> guyPrefers,
            Map<String, List<String>> girlPrefers) {
        if(!matches.keySet().containsAll(girls)){
            return false;
        }
 
        if(!matches.values().containsAll(guys)){
            return false;
        }
 
        Map<String, String> invertedMatches = new TreeMap<String, String>();
        for(Map.Entry<String, String> couple:matches.entrySet()){
            invertedMatches.put(couple.getValue(), couple.getKey());
        }
 
        for(Map.Entry<String, String> couple:matches.entrySet()){
            List<String> shePrefers = girlPrefers.get(couple.getKey());
            List<String> sheLikesBetter = new LinkedList<String>();
            sheLikesBetter.addAll(shePrefers.subList(0, shePrefers.indexOf(couple.getValue())));
            List<String> hePrefers = guyPrefers.get(couple.getValue());
            List<String> heLikesBetter = new LinkedList<String>();
            heLikesBetter.addAll(hePrefers.subList(0, hePrefers.indexOf(couple.getKey())));
 
            for(String guy : sheLikesBetter){
                String guysFinace = invertedMatches.get(guy);
                List<String> thisGuyPrefers = guyPrefers.get(guy);
                if(thisGuyPrefers.indexOf(guysFinace) >
                        thisGuyPrefers.indexOf(couple.getKey())){
                    System.out.printf("%s likes %s better than %s and %s"
                            + " likes %s better than their current partner\n",
                       couple.getKey(), guy, couple.getValue(),
                       guy, couple.getKey());
                    return false;
                }
            }
 
            for(String girl : heLikesBetter){
                String girlsFinace = matches.get(girl);
                List<String> thisGirlPrefers = girlPrefers.get(girl);
                if(thisGirlPrefers.indexOf(girlsFinace) >
                        thisGirlPrefers.indexOf(couple.getValue())){
                    System.out.printf("%s likes %s better than %s and %s"
                            + " likes %s better than their current partner\n",
                       couple.getValue(), girl, couple.getKey(),
                       girl, couple.getValue());
                    return false;
                }
            }
        }
        return true;
    }
}

