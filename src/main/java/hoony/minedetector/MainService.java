package hoony.minedetector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MainService {

    private final MainRepository mainRepository;

    @Autowired
    public MainService(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    // 지뢰 찾기 맵 생성
    public boolean[][] makeMineMap(int n, int m, int mineCount){
        boolean[][] mineMap = new boolean[n][m];
        int mineX, mineY;
        for (int i = 0; i < mineCount; i++) {
            mineX = (int)(Math.random()*n);
            mineY = (int)(Math.random()*m);
            while(mineMap[mineX][mineY]){
                mineX = (int)(Math.random()*n);
                mineY = (int)(Math.random()*m);
            }
            mineMap[mineX][mineY] = true;
        }
        makeMineAnswerMap(mineMap);

        return mineMap;
    }


    // 지뢰 찾기 정답 맵 생성 및 저장
    public void makeMineAnswerMap(boolean[][] mineMap){
        int[][] mineAnswerMap = new int[mineMap.length][mineMap[0].length];
        for (int i = 0; i < mineMap.length; i++) {
            for (int j = 0; j < mineMap[0].length; j++) {
                if(mineMap[i][j]){
                    mineAnswerMap[i][j] = -1;
                }else{
                    mineAnswerMap[i][j] = countMine(mineMap, i, j);
                }
            }
        }
        mainRepository.saveMineAnswerMap(mineAnswerMap);

//        return mineAnswerMap;
    }

    // 지뢰 갯수 카운트 (근방)
    public int countMine(boolean[][] mineMap, int x, int y){
        int count = 0;
        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                if(i < 0 || j < 0 || i >= mineMap.length || j >= mineMap[0].length){
                    continue;
                }
                if(mineMap[i][j]){
                    count++;
                }
            }
        }
        return count;
    }


    public int openCell(int x, int y) {
//        mainRepository.mineAnswerMap[x][y] 확인
        return mainRepository.mineAnswerMap[x][y];
    }

    public List<Map<String, Integer>> getAllMines() {
        return mainRepository.getAllMines();
    }
}
