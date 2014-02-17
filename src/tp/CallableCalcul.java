package tp;

import java.util.concurrent.Callable;

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
public class CallableCalcul implements Callable<Integer>{

    int max;


    public CallableCalcul(int max) {
        this.max = max;
    }

    @Override
    public Integer call() throws Exception {
    	int res = 0;
    	//somme de 0 �  b : 1+2+3+...+b
    	for (int i = 1; i <= max; i++) {
			res += i;
			System.out.println("rang : "+i+" de "+Thread.currentThread().getId());
		}
    	
    	return res;
    	
    }
}
