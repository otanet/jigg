package enju.ccg

import annotation.meta.getter

trait Options {
  type Option = fig.basic.Option @getter
  type OptionSet = fig.basic.OptionSet @getter
}

object DriverOptions extends Options {
  import OptionEnumTypes.{ModelType, ActionType, Language}

  @Option(gloss="Running model", required=true) var modelType:ModelType = _
  @Option(gloss="Running action", required=true) var actionType:ActionType = _
  @Option(gloss="Language") var language:Language = Language.japanese
}

object InputOptions extends Options {
  @Option(gloss = "Path to CCGBank directory (if this is set, files in this dir are used as default values of train/test and others)") var bankDirPath = ""
  @Option(gloss = "Path to training CCGBank") var trainPath = ""
  @Option(gloss = "Training instances, -1 for all") var trainSize = -1
  @Option(gloss = "Path to test CCGBank for evlauation") var testPath = ""
  @Option(gloss = "Path to develop CCGBank for evlauation") var developPath = ""
  @Option(gloss = "Test instances, -1 for all") var testSize = -1
  @Option(gloss = "Path to cabocha-format corpus for evaluating bunsetsu dependencies") var cabochaPath = ""

  @Option(gloss = "Path to Japanese category expantion definitions (required when training Tagger)") var templatePath = ""
  @Option(gloss = "Path to lexicon (word/pos -> category mappings)") var lexiconPath = ""

  @Option(gloss = "Path to trained model") var loadModelPath = ""
}

object OutputOptions extends Options {
  @Option(gloss = "Path to output of trained model after the training") var saveModelPath = ""
  @Option(gloss = "Path to write trained tagger model in a readable form") var taggerFeaturePath = "features.tagger.txt"
  @Option(gloss = "Path to write trained parser model in a readable form") var parserFeaturePath = "features.parser.txt"
  @Option(gloss = "Path to output tagger/parser predictions against test data") var outputPath = ""
}

object TrainingOptions extends Options {
  import OptionEnumTypes.StepSizeFunction

  @Option(gloss="Number of iterations") var numIters:Int = 10
  @Option(gloss="Whether removing zero weight features after each iteration") var removeZero:Boolean = false
}

object DictionaryOptions extends Options {
  import OptionEnumTypes.CategoryLookUpMethod
  @Option(gloss="How to look up category candidates? (for Japanese only)") var lookupMethod:CategoryLookUpMethod = CategoryLookUpMethod.surfaceAndSecondFineTag
  @Option(gloss="Whether using lexicon files to create word -> category mappings") var useLexiconFiles: Boolean = true
  @Option(gloss="Minimum number of occurences for registering as lexicalized entry") var unkThreathold = 15
}

object TaggerOptions extends Options {
  import OptionEnumTypes.TaggerTrainAlgorithm

  @Option(gloss="Beta for decising the threshold of k-best at prediction") var beta = 0.001
  @Option(gloss="Maximum number of k, -1 for no limit") var maxK = -1
  @Option(gloss="Parameter a of step size function = t^(-a) (used in sgd or cumulativeL1)") var stepSizeA = 0.2
  @Option(gloss="Parameter eta of AdaGrad") var eta = 0.1
  @Option(gloss="Reguralization strength called lambda (in AdaGrad) or C (in cumulative)") var lambda = 0.000000005

  @Option(gloss="Which training method is used at optimization of superTagger") var taggerTrainAlg = TaggerTrainAlgorithm.adaGradL1
}

object ParserOptions extends Options {
  @Option(gloss="Beam width; if k=1, deterministic decoder is used") var beam:Int = 8
  @Option(gloss="Prefer connected derivation at prediction") var preferConnected: Boolean = false
}

// object OP extends Options
