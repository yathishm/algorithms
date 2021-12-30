import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In a special ranking system, each voter gives a rank from highest to lowest to all teams participated in the competition.
 *
 * The ordering of teams is decided by who received the most position-one votes. If two or more teams tie in the first position, we consider the second position to resolve the conflict, if they tie again, we continue this process until the ties are resolved. If two or more teams are still tied after considering all positions, we rank them alphabetically based on their team letter.
 *
 * Given an array of strings votes which is the votes of all voters in the ranking systems. Sort all teams according to the ranking system described above.
 *
 * Return a string of all teams sorted by the ranking system.
 *
 *
 *
 * Example 1:
 *
 * Input: votes = ["ABC","ACB","ABC","ACB","ACB"]
 * Output: "ACB"
 * Explanation: Team A was ranked first place by 5 voters. No other team was voted as first place so team A is the first team.
 * Team B was ranked second by 2 voters and was ranked third by 3 voters.
 * Team C was ranked second by 3 voters and was ranked third by 2 voters.
 * As most of the voters ranked C second, team C is the second team and team B is the third.
 * Example 2:
 *
 * Input: votes = ["WXYZ","XYZW"]
 * Output: "XWYZ"
 * Explanation: X is the winner due to tie-breaking rule. X has same votes as W for the first position but X has one vote as second position while W doesn't have any votes as second position.
 * Example 3:
 *
 * Input: votes = ["ZMNAGUEDSJYLBOPHRQICWFXTVK"]
 * Output: "ZMNAGUEDSJYLBOPHRQICWFXTVK"
 * Explanation: Only one voter so his votes are used for the ranking.
 *
 *
 * Constraints:
 *
 * 1 <= votes.length <= 1000
 * 1 <= votes[i].length <= 26
 * votes[i].length == votes[j].length for 0 <= i, j < votes.length.
 * votes[i][j] is an English uppercase letter.
 * All characters of votes[i] are unique.
 * All the characters that occur in votes[0] also occur in votes[j] where 1 <= j < votes.length.
 */
public class RankTeamsByVotes {

    public String rankTeams(String[] votes) {
        Map<Character, TeamPositionData> groupedVoteCountByEachTeamMap = groupByVoteCountForEachTeam(votes);
        List<TeamPositionData> teamPositionDataList = new ArrayList<>(groupedVoteCountByEachTeamMap.values());
        Collections.sort(teamPositionDataList);
        StringBuilder result = new StringBuilder();
        for(TeamPositionData teamPositionData : teamPositionDataList){
            result.append(teamPositionData.team);
        }
        return result.toString();
    }

    /**
     * Return Map with count of votes of each position for each team.
     * @param votes
     * @return
     */
    private Map<Character, TeamPositionData> groupByVoteCountForEachTeam(String[] votes){
        Map<Character, TeamPositionData> groupByVoteMap = new HashMap<>();
        for(String vote : votes){
            for(int i = 0; i < vote.length(); i++){
                Character ch = vote.charAt(i);
                if(!groupByVoteMap.containsKey(ch)){
                    TeamPositionData teamPositionData = new TeamPositionData(ch, i, 1);
                    groupByVoteMap.put(ch, teamPositionData);
                } else
                    groupByVoteMap.get(ch).votes[i] = groupByVoteMap.get(ch).votes[i] + 1;
            }
        }
        return groupByVoteMap;
    }

    /**
     * A Custom class which contains each team's data.
     */
    class TeamPositionData implements Comparable<TeamPositionData> {
        Character team;
        int[] votes = new int[26];

        TeamPositionData(Character team, int position, int voteCount) {
            this.team = team;
            this.votes[position] =  voteCount;
        }

        @Override
        public int compareTo(TeamPositionData other) {
            int i = 0;
            for (; i < this.votes.length; ) {
                if (other.votes[i] > this.votes[i]) {
                    return 1;
                } else if (other.votes[i] < this.votes[i]) {
                    return -1;
                } else {
                    i++;
                }
            }
            return this.team.compareTo(other.team);
        }
    }
}


