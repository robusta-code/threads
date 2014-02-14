package tp;

import domain.Task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by  Nicolas Zozol for Robusta Code
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class Calcul {



    int resultA=0;
    int resultB=0;

    public int halfSuite(final int a, final int b) throws InterruptedException {



        resultA=0;
        resultB=0;
        Thread t1 = new Thread(){

            //somme de 0 a� a : 1+2+3+...+a
            public void run(){

            }
        };

        ////somme de 0 à b : 1+2+3+...+b
        Thread t2 = new Thread(){
            public void run(){

            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        return (resultA+resultB)/2;
    }

    public int halfSuiteWithWait(final int a, final int b) throws InterruptedException {


        resultA=0;
        resultB=0;

        final Object lock = new Object();

        Thread t1 = new Thread(){


            public void run(){


                //somme de 0 à a : 1+2+3+...+a

                synchronized (lock){

                    if (resultB>0){
                        //wait or notify ?
                    }else{
                        //wait or notify ?
                    }
                }

                Task task = new Task("Tweeting for A");
            }
        };

        ////somme de 0 à b : 1+2+3+...+b
        Thread t2 = new Thread(){
            public void run(){
                //somme de 0 à b : 1+2+3+...+b

                synchronized (lock){

                    if (resultA>0){
                        //wait or notify ?
                    }else{
                        //wait or notify ?
                    }
                }

                Task task = new Task("Tweeting for B");
            }
        };

        t1.start();
        t2.start();
        //no join here ! Don't want to wait the end of tweets

        // but a condition
        synchronized (lock){
            while (resultA== 0 && resultB ==0){
                lock.wait();
            }
        }

        return (resultA+resultB)/2;
    }


    public int whatHappensThere(int a, int b) throws InterruptedException {


        resultA=0;
        resultB=0;

        final Object lock = new Object();

        Thread t1 = new Thread(){


            public void run(){

                //On met la somme dans le synchronized pluto qu'en dehors
                synchronized (lock){
                    //somme de 0 à a : 1+2+3+...+a
                    if (resultB>0){
                        //wait or notify ?
                    }else{
                        //wait or notify ?
                    }
                }
            }
        };

        ////somme de 0 à b : 1+2+3+...+b
        Thread t2 = new Thread(){
            public void run(){

                //On met la somme dans le synchronized pluto qu'en dehors
                synchronized (lock){
                    //somme de 0 à b : 1+2+3+...+b
                    if (resultA>0){
                        //wait or notify ?
                    }else{
                        //wait or notify ?
                    }
                }
            }
        };


        synchronized (lock){
            while (resultA== 0 && resultB ==0){
                lock.wait();
            }
        }
        return (resultA+resultB)/2;
    }


    public int future(int a, int b) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CallableCalcul ca = new CallableCalcul(a);
        CallableCalcul cb = new CallableCalcul(a);

        Future<Integer> fA = executorService.submit(ca);
        // stuff with B

        int resultA = fA.get();

        // stuff with B

        //bad result
        return 0;


    }


}
