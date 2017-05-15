package runner;

public class AutoMatchRunner {
  private TeamDataBase tdb; //Stores the reference to the database so it can add data sets to it
  
  
  
  /*
   * Constructor for AutoMatchRunner
   * Requirements: database (TeamDataBase)
   */
  public AutoMatchRunner(TeamDataBase tdb) {
    this.tdb = tdb;
  }
  
  
  
  /*
   * Method for automagically adding a match to the database, regardless
   * Requirements: match (MatchResult)
   */
  public void autoAddMatch(MatchResult mr) {
    try {
      tdb.getTeamReference(mr.getData(Format.generalInfo, "Team Number").getData()).addMatch(mr);
    } catch (CouldNotFindException cnfe) {
      tdb.addTeam(mr.getData(Format.generalInfo, "Team Number").getData(),new ArrayList<MatchResult>(){{add(mr);}});
  }
}
