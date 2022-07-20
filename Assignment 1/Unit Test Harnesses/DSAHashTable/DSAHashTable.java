/*A version of this code has previously been submitted by me for Practical 7 DSA*/
class DSAHashTable
{
    private DSAHashEntry[] hashArray; 
    private Integer count;
    private Integer arrSize;

    public DSAHashTable(Integer tableSize)
    {
        if (!checkPrime(tableSize))
        {
            arrSize = nextPrime(tableSize);
        }
        else
        {
            arrSize = tableSize;
        }
        hashArray = new DSAHashEntry[arrSize];
        count = 0;
        for (int i = 0; i < arrSize; i++)
        {
            hashArray[i] = new DSAHashEntry();
        }
    }

    public DSAHashTable(DSAHashTable copyHashTable)
    {
        DSAHashEntry[] tempArray = copyHashTable.getArray();
        count = copyHashTable.getCount();
        arrSize = copyHashTable.getSize();
        hashArray = new DSAHashEntry[arrSize];
        for (int i = 0; i < arrSize; i++)
        {
            hashArray[i] = new DSAHashEntry(tempArray[i].getKey(), tempArray[i].getValue());
        }
    }

    public Integer getCount()
    {
        return count;
    }

    public Integer getSize()
    {
        return arrSize;
    }

    public void put(String inKey, Integer inValue)
    {

        Integer location = hash(inKey);
        boolean inserted = false;

        if (getLoadFactor() >= 0.75)
        {
            resize();
        }

        if (hashArray[location].getKey() == "")
        {    
            hashArray[location] = new DSAHashEntry(inKey, inValue);
            hashArray[location].setState();
            count = count + 1;
        }
        else
        {
            Integer stepDistance = stepHash(inKey);
            do
            {
                location = location + stepDistance;
                location = location % arrSize;
                
                if (hashArray[location].getKey() == "")
                {
                    inserted = true;
                    hashArray[location] = new DSAHashEntry(inKey, inValue);
                    hashArray[location].setState();
                    count = count + 1;
                }
            } while (!inserted);
        }

    }

    public DSAHashEntry[] getArray()
    {
        return hashArray;
    }

    private Integer hash(String inKey)
    {
        char c = inKey.charAt(0);
        Integer arrIndex = (int) c % arrSize;
        return arrIndex;
    }

    private Integer stepHash(String inKey)
    {
        char c = inKey.charAt(0);
        Integer returnStep = 3 - ((int) c % arrSize % 3);
        return returnStep;
    }

    public DSAHashEntry get(String inKey)
    {
        DSAHashEntry returnEntry = find(inKey);
        return returnEntry;
    }

    public void remove(String inKey)
    {
        DSAHashEntry hashEntry = find(inKey);
        if (hashEntry.getKey() != "")
        {
            hashEntry.setKey("");
            hashEntry.setValue(null);
            count = count - 1;
            resize();
        }
    }

    private DSAHashEntry find(String inKey)
    {
        Integer location = hash(inKey);
        DSAHashEntry returnDSAHashEntry = null;

        if (hashArray[location].getKey().equals(inKey))
        {
            returnDSAHashEntry = hashArray[location];
        }
        else
        {
            if (hashArray[location].getState() == 1)
            {
                Integer stepDistance = stepHash(inKey);
                do
                {
                    location = location + stepDistance;
                    location = location % arrSize;
                    if (hashArray[location].getKey().equals(inKey))
                    {
                        returnDSAHashEntry = hashArray[location];
                    }

                } while (hashArray[location].getState() == 1 || hashArray[location].getKey().equals(inKey));
            }
        }
        return returnDSAHashEntry;
    }

    private Double getLoadFactor()
    {
        Double dCount = Double.valueOf(count);
        Double dArrSize = Double.valueOf(arrSize);
        return ( dCount / dArrSize);
    }

    public void export()
    {
        for (int i = 0; i < arrSize; i++)
        {
            System.out.println(hashArray[i].getKey() + ":" + hashArray[i].getValue());
            // if (hashArray[i].getKey() != "")
            // {
            //     System.out.println(hashArray[i].getKey() + ":" + hashArray[i].getValue());
            // }
        }
    }

    private void resize()
    {
        Integer oldSize = arrSize;
        Integer oldCount = count;

        while (getLoadFactor() >= 0.75)
        {
            arrSize = nextPrime(arrSize);
        }

        while (getLoadFactor() <= 0.30)
        {
            arrSize = arrSize / 6 * 2;
            arrSize = nextPrime(arrSize);
        }

        DSAHashEntry[] copyTable = hashArray;

        hashArray = new DSAHashEntry[arrSize];
        for (int i = 0; i < arrSize; i++)
        {
            hashArray[i] = new DSAHashEntry();
        }
        
        count = 0; 

        for (int i = 0; i < oldSize; i++)
        {
            if (copyTable[i].getKey() != "")
            {
                put(copyTable[i].getKey(), copyTable[i].getValue());
            }
        }
        count = oldCount;
    }

    private Integer nextPrime(Integer oldSize)
    {
        oldSize++;
        for (int i = 2; i < oldSize; i++)
        {
            if (oldSize % i == 0)
            {
                oldSize++;
                i = 2;
            }
        }

        return oldSize;
    }

    private boolean checkPrime(Integer loopNumber)
    {
        boolean returnBool = false;
        if (loopNumber % 2 != 0)
        {
            for (int i = 3; i * i <= loopNumber; i += 2)
            {
                if (loopNumber % i != 0)
                {
                    returnBool = true;
                }
            }
        }
        return returnBool;
    }


}