package hoony.minedetector;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MainRepository {
    int[][] mineAnswerMap;

    // 생성된 지뢰 찾기 정답 맵 저장
    public void saveMineAnswerMap(int[][] mineAnswerMap){
        this.mineAnswerMap = mineAnswerMap;
        printMineAnswerMap();
    }

    // 정답 맵 출력
    public void printMineAnswerMap(){
        System.out.println("<지뢰찾기 정답 맵>");
        for (int i = 0; i < mineAnswerMap.length; i++) {
            for (int j = 0; j < mineAnswerMap[0].length; j++) {
                if(mineAnswerMap[i][j] == -1)  System.out.print("X ");
                else System.out.print(mineAnswerMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    public List<Map<String, Integer>> getAllMines() {
        List<Map<String, Integer>> mines = new ArrayList<>();
        for (int i = 0; i < mineAnswerMap.length; i++) {
            for (int j = 0; j < mineAnswerMap[0].length; j++) {
                if (mineAnswerMap[i][j] == -1) {
                    Map<String, Integer> mine = new HashMap<>();
                    mine.put("x", i);
                    mine.put("y", j);
                    mines.add(mine);
                }
            }
        }
        return mines;
    }



}
