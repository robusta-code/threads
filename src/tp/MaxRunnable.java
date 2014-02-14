package tp;

import domain.Task;

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
public class MaxRunnable implements Runnable{


    int max;
    int result=0;
    boolean finished;
    //Other runnable who may have finished
    MaxRunnable friend;
    Object lock;

    public MaxRunnable(int max, Object lock) {
        this.max = max;
        this.lock = lock;
    }

    public void setFriend(MaxRunnable friend) {
        this.friend = friend;
    }

    @Override
    public void run() {
        //somme de 0 à max

        synchronized (lock){

            if (/* what condition ? */ true){
                //wait or notify ?
            }else{
                //wait or notify ?
            }
        }

        //Do other Stuf
        Task task = new Task("Tweeting the world");
        System.out.println(task);

    }

    public int getResult() {
        return result;
    }

    public boolean isFinished() {
        return finished;
    }
}
