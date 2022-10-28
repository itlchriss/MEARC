cat $1 | sed -f ../en/tokenizer.sed > sentences.tok
../candc-1.00/bin/candc  --log ./candc.log --models ../candc-1.00/models --candc-printer xml --input sentences.tok > sentences.candc.xml
../py3/bin/python ../en/candc2transccg_wsc.py sentences.candc.xml > sentences.xml
../py3/bin/python ../scripts/semparse_wsc.py sentences.xml ../en/semantic_templates_en_emnlp2015.yaml

rm *.xml
rm *.tok
rm candc.log
