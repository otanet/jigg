package jigg.nlp.ccg

/*
 Copyright 2013-2016 Hiroshi Noji

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/

import breeze.config.CommandLineParser

object TrainSuperTagger {

  import SuperTaggerTrainer.Params

  def main(args: Array[String]) = {

    val params = CommandLineParser.readIn[Params](args)
    val trainer = mkTrainer(params)
    trainer.trainAndSave()
  }

  def mkTrainer(params: Params): SuperTaggerTrainer = params.bank.lang match {
    case "ja" => new JapaneseSuperTaggerTrainer(params)
    case "en" => new EnglishSuperTaggerTrainer(params)
  }
}
