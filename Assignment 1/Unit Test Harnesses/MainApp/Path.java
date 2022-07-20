class Path
{
    private String pathName = "";
    private Integer pathVal = null;

    public Path(String inPathName, Integer inPathVal)
    {
        pathName = inPathName;
        pathVal = inPathVal;
    }
    
    public Integer getWeight()
    {
        return pathVal;
    }

    public String getName()
    {
        return pathName;
    }
}