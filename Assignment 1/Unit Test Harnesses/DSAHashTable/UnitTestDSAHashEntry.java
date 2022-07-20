class UnitTestDSAHashEntry
{
    public static void main(String[] args)
    {
        System.out.println("Creating new Hash Table");
        DSAHashTable testTable = new  DSAHashTable(5);
        System.out.println("Exporting table (Should export nothing), but table will automatically resize to next Prime Number");
        testTable.export();
        System.out.println("Adding a key 'Sahas' and Integer '211000666'");
        testTable.put("Sahas", 211000666);
        testTable.export();
        System.out.println("Adding a key 'Senith' with Integer '20006666'");
        testTable.put("Senith", 20006666);
        testTable.export();
        System.out.println("Because of the way the algorithm is written to calculate Hash, Sahas and Senith will have a collission");
        System.out.println("Removing Sahas");
        testTable.remove("Sahas");
        testTable.export();
        System.out.println("Searching for Senith");
        DSAHashEntry searchEntry = testTable.get("Senith");
        System.out.println(searchEntry.getKey() + " : " + searchEntry.getValue());
        System.out.println("Searching for Sahas (Which we have deleted), we get null back");
        DSAHashEntry nullEntry = testTable.get("Sahas");
        System.out.println(nullEntry);
        System.out.println("Adding more than 7 values, to see if it resizes");
        testTable.put("Sahas", 211000666);
        testTable.put("Shanya", 204093626);
        testTable.put("Serika", 211000412);
        testTable.put("Lolonyo", 201293132);
        testTable.put("Thithira", 201831872);
        testTable.put("Anura", 20138273);
        testTable.export();
        System.out.println("As you can see it has resized to an HashTable of size 11 (Prime Number)");
        System.out.println("Removing a substantial amount of items from the HashTable");
        testTable.remove("Sahas");
        testTable.remove("Shanya");
        testTable.remove("Serika");
        testTable.remove("Lolonyo");
        testTable.remove("Thithira");
        testTable.export();
        System.out.println("As you can see the HashTable has resized to a size of 3, keeping LF between 0.30 and 0.75");
    }
}