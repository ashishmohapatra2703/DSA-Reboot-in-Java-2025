/*https://leetcode.com/problems/insert-delete-getrandom-o1/*/

class RandomizedSet {
    private ArrayList<Integer> list;
    private HashMap<Integer, Integer> hashMap; //<num, idx>
    private Random random;

    public RandomizedSet() {
        list = new ArrayList<>();
        hashMap = new HashMap<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
        if(hashMap.containsKey(val))
            return false;
        list.add(val); 
        hashMap.put(val, list.size()-1); //added at the end => idx = list.size()-1
        return true;
    }
    
    public boolean remove(int val) {
        if(!hashMap.containsKey(val))
            return false;

        int idxOfNumToRemove = hashMap.get(val);
        int idxOfLastNum = list.size()-1;
        int lastNum = list.get(idxOfLastNum);

        list.set(idxOfNumToRemove, lastNum); //replace lastNum in place of removalValue
        hashMap.put(lastNum, idxOfNumToRemove); //update map

        list.remove(idxOfLastNum); //remove last Num
        hashMap.remove(val); //remove the val from hashMap
        return true;
    }
    
    public int getRandom() {
        int sizeOfList = list.size();
        int randIdx = random.nextInt(sizeOfList); //[0,sizeOfList)
        return list.get(randIdx);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */