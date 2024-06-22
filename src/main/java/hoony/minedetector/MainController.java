package hoony.minedetector;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
//@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/")
    public String hello() {

        return "home"; // 이 값은 templates/hello.html 파일을 찾습니다.
    }

    @PostMapping("/makeMineMap")
    @ResponseBody
    public void makeMineMap(@RequestBody Map<String, Integer> params){
        int n = params.get("n");
        int m = params.get("m");
        int mineCount = params.get("mineCount");
//        return mainService.makeMineMap(n, m, mineCount);
        mainService.makeMineMap(n, m, mineCount);
    }

    @PostMapping("/openCell")
    @ResponseBody
    public String openCell(@RequestBody Map<String, Integer> params){
        int x = params.get("x");
        int y = params.get("y");
        int answer = mainService.openCell(x, y);

        if(answer == -1){
            return "mine";
        }
        else
            return String.valueOf(answer);

    }

    @GetMapping("/getAllMines")
    @ResponseBody
    public List<Map<String, Integer>> getAllMines() {
        return mainService.getAllMines();
    }
}
