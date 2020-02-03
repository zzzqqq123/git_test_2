package com.utils.codecover;

import org.springframework.stereotype.Component;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.codecover
 * @ClassName: TestCodeCover
 * @Author: zhangqiang
 * @Description: 测试代码覆盖率
 * @Date: 2019/12/29 9:20 下午
 * @Version: 1.0
 */
@Component
public class TestCodeCover {
    private String getValve() {
        System.out.println("h");
        int i=0;
        if(i>0){
            System.out.println("1");
        }else{
            System.out.println("0");
        }
        return "";
    }
    public void tets2() {
        this.getValve();
    }
}
