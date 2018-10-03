import java.util.*;


public class Hearthstone {
    public ArrayList<String> openPack(ArrayList<String> common, ArrayList<String> rare,ArrayList<String> epic, ArrayList<String> legendary){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < 5; i++){
            Random rand = new Random();
            int rarity = rand.nextInt(100) + 1;
            if(rarity <= 64){
                int commoncard = rand.nextInt(common.size());
                result.add(common.get(commoncard));
            }
            else if(rarity > 64 && rarity <= 89){
                int rarecard = rand.nextInt(rare.size());
                result.add(rare.get(rarecard));
            }
            else if(rarity > 89 && rarity <= 99){
                int epiccard = rand.nextInt(epic.size());
                result.add(epic.get(epiccard));
            }
            else if(rarity == 100){
                int legendcard = rand.nextInt(legendary.size());
                result.add(rare.get(legendcard));
            }
        }
        return result;
    }
}
