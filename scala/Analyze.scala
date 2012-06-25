/**
 * Copyright 2012 Michael N. Gagnon
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************
 *
 * "Read a file of text, determine the n most frequently used words, and print
 *  out a sorted list of those words along with their frequencies."
 * 		-- Jon Bentley's 1986 challenge to Donald Knuth and Doug McIlroy,
 *		   http://www.leancrew.com/all-this/2011/12/more-shell-less-egg/		
 *
 * @author Michael N. Gagnon
 * 
 */ 
  
import io.Source

object Analyze {
    def main(args: Array[String]) {

        val n = args(1).toInt

        val words = Source.fromFile(args(0))
            .getLines
            .flatMap {
                _.split("\\s+")
                .filter {
                    _.nonEmpty
                }
            }
            .toList
        
        val totalCount = words.size.toFloat

        words
            .toList
            .groupBy { x => x }
            .toList
            .map { case(word, words) =>
                (words.size, word)
            }
            .sorted(Ordering[(Int, String)].reverse)
            .take(n)
            .map { case(count, word) => 
                println("%20s %15f".format(word, count.toFloat / totalCount))
            }
    }
}
