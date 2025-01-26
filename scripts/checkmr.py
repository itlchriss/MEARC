

from glob import glob
import os



expected_wrong = {
    'starchat': [
        './test/s0231_power_of_two/hafis/starchat/tmp/post.5.mr', # use of 'there'
        './test/s0231_power_of_two/hafis/starchat/tmp/post.6.mr', # use of 'there'
        './test/s0219_contains_duplicate_ii/hafis/starchat/tmp/post.0.mr', # use of 'there'
        './test/s0219_contains_duplicate_ii/hafis/starchat/tmp/post.1.mr', # use of 'there'
        './test/s0008_string_to_integer_atoi/hafis/starchat/tmp/pre.2.mr', # incomplete sentence structure
    ],
    'gpt35': [
        './test/s0860_lemonade_change/hafis/gpt35/tmp/post.1.mr', # use of 'there'
        './test/s0560_subarray_sum_equals_k/hafis/gpt35/tmp/post.0.mr', # use of interrogative pronoun
    ]
}

def calc(model: str):
    SRCPATH = "./test/s*/hafis"
    data = []
    failed = []
    # refer to incomplete MR beta reduction
    incomplete = []
    for folder in glob(SRCPATH):    
        with open(os.path.join(folder, model, "tmp/conditions.yml"), "r") as fp:
            lines = fp.read()
        temp = "%s.%s.mr"
        head = ""
        count = 0    
        for line in lines.split("\n"):
            if line.startswith("requires"):
                head = "pre"
                count = 0
            elif line.startswith("ensures"):
                head = "post"
                count = 0
            elif not line.startswith("-"):
                break
            else:
                data.append(os.path.join(folder, model, "tmp", temp % (head, str(count))))
                count += 1
        for file in glob(os.path.join(folder, model, "tmp/*.mr")):
            with open(file, "r") as fp:
                _tmp = fp.read()
                if _tmp and _tmp.strip() == "Failed":
                    failed.append(file)
                elif _tmp and '\\' in _tmp.strip():
                    incomplete.append(file)
            data.remove(file)
    return data, failed, incomplete

for model in ["starchat", "gpt35", "gpt4"]:
    data, failed, incomplete = calc(model)
    if data:
        print("%s Missing:" % model)
        print("\n".join(data))
        print("count: ", len(data))

    if failed:
        print("%s Failed due to NLP cannot parse the sentence" % model)
        for i in failed:
            if i in expected_wrong[model]:
                print(i, " --> Failed as expected")
            else:
                print(i)
        print("count: ", len(failed))

    if incomplete:
        print("%s Incomplete MR beta reduction" % model)
        for i in incomplete:
            if i in expected_wrong[model]:
                print(i, " --> Failed as expected")
            else:
                print(i)
        print("count: ", len(incomplete))