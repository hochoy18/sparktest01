package com.hochoy.jvm.memorymanage;

/**
 * Describe:
 *
 * @author hochoy <hochoy18@sina.com>
 * @version V1.0.0
 * @date 2019/7/28
 */
public class GCTest {

    public static void main(String[] args) {

    }



    static void FinalizeEscapeGC(){


    }



}
class FinalizeEscapeGC{
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes ,I am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize() executed ....");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }
    static void test() throws Throwable{
        SAVE_HOOK = new  FinalizeEscapeGC();
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);

        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no ,i am dead:(");
        }
        SAVE_HOOK = null;
        System.gc();
    }
}