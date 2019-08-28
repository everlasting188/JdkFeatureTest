nlp: 测试相关的nlp算法
crf: https://github.com/asher-stern/CRF

For those who are interested only in the CRF but not the POS-tagger, an example entry point is com.asher_stern.crf.crf.run.ExampleMain.

Note that this entry point is only a skeleton, and the user should copy it, and implement the feature generator and other stuff required to run the CRF for the user's specific problem.

Those who are interested in the POS-tagging example can use the following two entry points: com.asher_stern.crf.postagging.demo.TrainAndEvaluate and com.asher_stern.crf.postagging.demo.UsePosTagger.

HMM-Viterbi Algorithm : https://github.com/hankcs/Viterbi