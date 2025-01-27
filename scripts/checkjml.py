

from glob import glob
import os



expected_wrong = {
    'starchat': [
'./test/s0392_is_subsequence/hafis/starchat/jml/pre.4.jml', # the LLM has failed. the sentence is cut due to the return size of response,
'./test/s0238_product_of_array_except_self/hafis/starchat/jml/post.1.jml', # missing information. division operation in integer array is unknown
'./test/s0238_product_of_array_except_self/hafis/starchat/jml/post.2.jml', # future work. we cannot specify time complexity in JML
'./test/s0238_product_of_array_except_self/hafis/starchat/jml/post.3.jml', # future work. we cannot specify time complexity in JML
'./test/s0151_reverse_words_in_a_string/hafis/starchat/jml/pre.2.jml', # missing information. word is not defined
'./test/s0001_two_sum/hafis/starchat/jml/post.2.jml', # missing information. indices with reference to what is missing
'./test/s0001_two_sum/hafis/starchat/jml/post.3.jml', # missing information. indices with reference to what is missing
'./test/s0925_long_pressed_name/hafis/starchat/jml/post.0.jml', # missing information. long press is not defined
'./test/s0925_long_pressed_name/hafis/starchat/jml/post.1.jml', # missing information. long press is not defined
'./test/s0227_basic_calculator_ii/hafis/starchat/jml/post.0.jml', # wrong specification. result should be only one but results is used
'./test/s0227_basic_calculator_ii/hafis/starchat/jml/pre.2.jml', # missing information. valid expression is not defined
'./test/s0331_verify_preorder_serialization_of_a_binary_tree/hafis/starchat/jml/pre.1.jml',
'./test/s0219_contains_duplicate_ii/hafis/starchat/jml/post.0.jml',
'./test/s0219_contains_duplicate_ii/hafis/starchat/jml/post.1.jml',
'./test/s0219_contains_duplicate_ii/hafis/starchat/jml/pre.1.jml',
'./test/s0521_longest_uncommon_subsequence_i/hafis/starchat/jml/post.0.jml',
'./test/s0521_longest_uncommon_subsequence_i/hafis/starchat/jml/pre.0.jml',
'./test/s0521_longest_uncommon_subsequence_i/hafis/starchat/jml/pre.1.jml',
'./test/s0521_longest_uncommon_subsequence_i/hafis/starchat/jml/pre.2.jml',
'./test/s0164_maximum_gap/hafis/starchat/jml/post.0.jml',
'./test/s0390_elimination_game/hafis/starchat/jml/post.3.jml',
'./test/s0390_elimination_game/hafis/starchat/jml/post.4.jml',
'./test/s0390_elimination_game/hafis/starchat/jml/post.5.jml',
'./test/s0390_elimination_game/hafis/starchat/jml/pre.1.jml',
'./test/s1013_partition_array_into_three_parts_with_equal_sum/hafis/starchat/jml/post.0.jml',
'./test/s0055_jump_game/hafis/starchat/jml/post.0.jml',
'./test/s0055_jump_game/hafis/starchat/jml/post.1.jml',
'./test/s0055_jump_game/hafis/starchat/jml/post.2.jml',     # wrong data type. result is integer and is going to be compared to boolean
'./test/s0055_jump_game/hafis/starchat/jml/post.3.jml',     # wrong data type. result is integer and is going to be compared to boolean
'./test/s0263_ugly_number/hafis/starchat/jml/post.39.jml',
'./test/s0888_fair_candy_swap/hafis/starchat/jml/post.0.jml',
'./test/s0888_fair_candy_swap/hafis/starchat/jml/post.1.jml',
'./test/s0888_fair_candy_swap/hafis/starchat/jml/post.2.jml',
'./test/s0028_find_the_index_of_the_first_occurrence_in_a_string/hafis/starchat/jml/post.6.jml',
'./test/s0033_search_in_rotated_sorted_array/hafis/starchat/jml/post.0.jml',
'./test/s0033_search_in_rotated_sorted_array/hafis/starchat/jml/post.1.jml',
'./test/s0033_search_in_rotated_sorted_array/hafis/starchat/jml/pre.2.jml',
'./test/s0033_search_in_rotated_sorted_array/hafis/starchat/jml/pre.3.jml',
'./test/s1021_remove_outermost_parentheses/hafis/starchat/jml/post.0.jml',
'./test/s1021_remove_outermost_parentheses/hafis/starchat/jml/post.1.jml',
'./test/s1021_remove_outermost_parentheses/hafis/starchat/jml/post.2.jml',
'./test/s1021_remove_outermost_parentheses/hafis/starchat/jml/pre.1.jml',
'./test/s1021_remove_outermost_parentheses/hafis/starchat/jml/pre.2.jml',
'./test/s0165_compare_version_numbers/hafis/starchat/jml/post.0.jml',
'./test/s0165_compare_version_numbers/hafis/starchat/jml/pre.0.jml',
'./test/s0165_compare_version_numbers/hafis/starchat/jml/pre.1.jml',
'./test/s0165_compare_version_numbers/hafis/starchat/jml/pre.2.jml',
'./test/s0091_decode_ways/hafis/starchat/jml/pre.2.jml',
'./test/s0405_convert_a_number_to_hexadecimal/hafis/starchat/jml/post.1.jml',
'./test/s0405_convert_a_number_to_hexadecimal/hafis/starchat/jml/post.2.jml',
'./test/s0415_add_strings/hafis/starchat/jml/post.0.jml',
'./test/s0415_add_strings/hafis/starchat/jml/post.1.jml',
'./test/s0415_add_strings/hafis/starchat/jml/pre.4.jml',
'./test/s0258_add_digits/hafis/starchat/jml/post.3.jml',
'./test/s0217_contains_duplicate/hafis/starchat/jml/post.0.jml',
'./test/s0217_contains_duplicate/hafis/starchat/jml/post.1.jml',
'./test/s0504_base_7/hafis/starchat/jml/post.1.jml',
'./test/s0504_base_7/hafis/starchat/jml/post.2.jml',
'./test/s0551_student_attendance_record_i/hafis/starchat/jml/post.0.jml',
'./test/s0066_plus_one/hafis/starchat/jml/post.2.jml',
'./test/s0066_plus_one/hafis/starchat/jml/pre.2.jml',
'./test/s0008_string_to_integer_atoi/hafis/starchat/jml/pre.2.jml',
'./test/s0072_edit_distance/hafis/starchat/jml/post.0.jml',
'./test/s0961_n_repeated_element_in_size_2n_array/hafis/starchat/jml/pre.1.jml',
'./test/s0961_n_repeated_element_in_size_2n_array/hafis/starchat/jml/pre.3.jml',
'./test/s0961_n_repeated_element_in_size_2n_array/hafis/starchat/jml/pre.4.jml',
'./test/s0326_power_of_three/hafis/starchat/jml/post.0.jml',
'./test/s0326_power_of_three/hafis/starchat/jml/post.1.jml',
'./test/s0454_4sum_ii/hafis/starchat/jml/post.0.jml',
'./test/s0454_4sum_ii/hafis/starchat/jml/post.2.jml',
'./test/s0454_4sum_ii/hafis/starchat/jml/post.3.jml',
'./test/s0454_4sum_ii/hafis/starchat/jml/pre.0.jml',
'./test/s0454_4sum_ii/hafis/starchat/jml/pre.1.jml',
'./test/s0454_4sum_ii/hafis/starchat/jml/pre.2.jml',
'./test/s0454_4sum_ii/hafis/starchat/jml/pre.3.jml',
'./test/s0454_4sum_ii/hafis/starchat/jml/pre.4.jml',
'./test/s0561_array_partition_i/hafis/starchat/jml/post.0.jml',
'./test/s0561_array_partition_i/hafis/starchat/jml/post.1.jml',
'./test/s0561_array_partition_i/hafis/starchat/jml/pre.1.jml',
'./test/s0520_detect_capital/hafis/starchat/jml/post.0.jml',
'./test/s0520_detect_capital/hafis/starchat/jml/post.1.jml',
'./test/s0171_excel_sheet_column_number/hafis/starchat/jml/pre.2.jml',
'./test/s0198_house_robber/hafis/starchat/jml/post.0.jml',
'./test/s0941_valid_mountain_array/hafis/starchat/jml/pre.1.jml',
'./test/s0941_valid_mountain_array/hafis/starchat/jml/pre.2.jml',
'./test/s0006_zigzag_conversion/hafis/starchat/jml/pre.1.jml',
'./test/s0342_power_of_four/hafis/starchat/jml/post.0.jml',
'./test/s0342_power_of_four/hafis/starchat/jml/post.1.jml',
'./test/s0412_fizz_buzz/hafis/starchat/jml/post.1.jml',
'./test/s0053_maximum_subarray/hafis/starchat/jml/post.0.jml',
'./test/s0168_excel_sheet_column_title/hafis/starchat/jml/post.0.jml',
'./test/s0168_excel_sheet_column_title/hafis/starchat/jml/post.2.jml',
'./test/s0202_happy_number/hafis/starchat/jml/post.0.jml',
'./test/s0202_happy_number/hafis/starchat/jml/post.5.jml',
'./test/s0202_happy_number/hafis/starchat/jml/pre.1.jml',
'./test/s0202_happy_number/hafis/starchat/jml/pre.2.jml',
'./test/s0202_happy_number/hafis/starchat/jml/pre.3.jml',
'./test/s0202_happy_number/hafis/starchat/jml/pre.4.jml',
'./test/s0202_happy_number/hafis/starchat/jml/pre.5.jml',
'./test/s0202_happy_number/hafis/starchat/jml/pre.6.jml',
'./test/s0202_happy_number/hafis/starchat/jml/pre.7.jml',
'./test/s0202_happy_number/hafis/starchat/jml/pre.8.jml',
'./test/s0202_happy_number/hafis/starchat/jml/pre.9.jml',
'./test/s0461_hamming_distance/hafis/starchat/jml/post.0.jml',
'./test/s0461_hamming_distance/hafis/starchat/jml/post.1.jml',
'./test/s0461_hamming_distance/hafis/starchat/jml/post.2.jml',
'./test/s0067_add_binary/hafis/starchat/jml/post.0.jml',
'./test/s0067_add_binary/hafis/starchat/jml/pre.4.jml',
'./test/s0058_length_of_last_word/hafis/starchat/jml/pre.2.jml',
'./test/s0451_sort_characters_by_frequency/hafis/starchat/jml/post.0.jml',
'./test/s0451_sort_characters_by_frequency/hafis/starchat/jml/post.1.jml',
'./test/s0451_sort_characters_by_frequency/hafis/starchat/jml/post.2.jml',
'./test/s0228_summary_ranges/hafis/starchat/jml/post.0.jml',
'./test/s0228_summary_ranges/hafis/starchat/jml/post.1.jml',
'./test/s0228_summary_ranges/hafis/starchat/jml/post.2.jml',
'./test/s0228_summary_ranges/hafis/starchat/jml/post.3.jml',
'./test/s0496_next_greater_element_i/hafis/starchat/jml/post.0.jml',
'./test/s0496_next_greater_element_i/hafis/starchat/jml/post.1.jml',
'./test/s0496_next_greater_element_i/hafis/starchat/jml/post.2.jml',
'./test/s0496_next_greater_element_i/hafis/starchat/jml/pre.4.jml',
'./test/s0496_next_greater_element_i/hafis/starchat/jml/pre.5.jml',
'./test/s0299_bulls_and_cows/hafis/starchat/jml/post.0.jml',
'./test/s0299_bulls_and_cows/hafis/starchat/jml/pre.1.jml',
'./test/s0134_gas_station/hafis/starchat/jml/post.0.jml',
'./test/s0134_gas_station/hafis/starchat/jml/post.1.jml',
'./test/s0942_di_string_match/hafis/starchat/jml/post.0.jml',
'./test/s0231_power_of_two/hafis/starchat/jml/post.5.jml',
'./test/s0231_power_of_two/hafis/starchat/jml/post.6.jml',
'./test/s0309_best_time_to_buy_and_sell_stock_with_cooldown/hafis/starchat/jml/post.0.jml',
'./test/s0065_valid_number/hafis/starchat/jml/post.17.jml',
'./test/s0065_valid_number/hafis/starchat/jml/post.18.jml',
'./test/s0065_valid_number/hafis/starchat/jml/post.19.jml',
'./test/s0065_valid_number/hafis/starchat/jml/post.20.jml',
'./test/s0065_valid_number/hafis/starchat/jml/post.21.jml',
'./test/s0065_valid_number/hafis/starchat/jml/post.22.jml',
'./test/s0065_valid_number/hafis/starchat/jml/post.23.jml',
'./test/s0292_nim_game/hafis/starchat/jml/post.41.jml',

    ],
    'gpt35': [
        './test/s0020_valid_parentheses/hafis/gpt35/jml/post.0.jml', # missing information
'./test/s0020_valid_parentheses/hafis/gpt35/jml/post.1.jml', # missing information
'./test/s0989_add_to_array_form_of_integer/hafis/gpt35/jml/post.0.jml', # too complicated. we need to advance the prompt
'./test/s0989_add_to_array_form_of_integer/hafis/gpt35/jml/post.1.jml', # too complicated. we need to advance the prompt
'./test/s0191_number_of_1_bits/hafis/gpt35/jml/post.0.jml', # too complicated. need runtime support of parsing type to type
'./test/s0191_number_of_1_bits/hafis/gpt35/jml/pre.0.jml', # too complicated. need runtime support of parsing type to type
'./test/s0238_product_of_array_except_self/hafis/gpt35/jml/post.1.jml', # too complicated. we need to advance the prompt
'./test/s0238_product_of_array_except_self/hafis/gpt35/jml/post.2.jml', # missing information. suffix and prefix of array is not defined
'./test/s0151_reverse_words_in_a_string/hafis/gpt35/jml/post.1.jml', # too complicated. we need to advance the prompt
'./test/s0151_reverse_words_in_a_string/hafis/gpt35/jml/post.2.jml', # too complicated. we need to advance the prompt
'./test/s0151_reverse_words_in_a_string/hafis/gpt35/jml/pre.2.jml', # missing information. word is not defined
'./test/s0229_majority_element_ii/hafis/gpt35/jml/post.0.jml', # missing information
'./test/s0001_two_sum/hafis/gpt35/jml/post.0.jml',  # too complicated
'./test/s0001_two_sum/hafis/gpt35/jml/post.1.jml',  # missing information. indices with reference to what is missing
'./test/s0001_two_sum/hafis/gpt35/jml/post.2.jml',  # useless information. the specification says order does not matter
'./test/s0925_long_pressed_name/hafis/gpt35/jml/post.2.jml', # missing information. long press is not defined
'./test/s0925_long_pressed_name/hafis/gpt35/jml/post.3.jml', # missing information. long press is not defined
'./test/s0319_bulb_switcher/hafis/gpt35/jml/post.0.jml', # missing information. `bulbs` is not in program
'./test/s0227_basic_calculator_ii/hafis/gpt35/jml/post.0.jml', # missing information. the program has only one result but `results` is used
'./test/s0227_basic_calculator_ii/hafis/gpt35/jml/pre.0.jml', # missing information. valid expression is not defined
'./test/s0331_verify_preorder_serialization_of_a_binary_tree/hafis/gpt35/jml/post.0.jml', # missing information. preorder traversal serialization is not defined
'./test/s0331_verify_preorder_serialization_of_a_binary_tree/hafis/gpt35/jml/pre.1.jml', # ?
'./test/s0476_number_complement/hafis/gpt35/jml/post.0.jml', # too complicated. need runtime support for parsing int to binary
'./test/s0219_contains_duplicate_ii/hafis/gpt35/jml/post.0.jml', # using there. we need to advance the prompt
'./test/s0219_contains_duplicate_ii/hafis/gpt35/jml/post.1.jml', # using there. we need to advance the prompt
'./test/s0628_maximum_product_of_three_numbers/hafis/gpt35/jml/post.0.jml', # too complicated. product of the 3 largest values needs runtime support
'./test/s0026_remove_duplicates_from_sorted_array/hafis/gpt35/jml/post.1.jml', # missing information. `k` is not defined in the program but it is used as a count
'./test/s0026_remove_duplicates_from_sorted_array/hafis/gpt35/jml/pre.1.jml', # too complicated. we need to advance the prompt
'./test/s0390_elimination_game/hafis/gpt35/jml/post.0.jml', # missing information. elimination algorithm is not defined
'./test/s1013_partition_array_into_three_parts_with_equal_sum/hafis/gpt35/jml/post.0.jml', # too complicated. we need to advance the prompt. `we` the pronoun is used
'./test/s0055_jump_game/hafis/gpt35/jml/post.0.jml', # too complicated. we need to advance the prompt
'./test/s0055_jump_game/hafis/gpt35/jml/post.1.jml', # too complicated. we need to advance the prompt
'./test/s0560_subarray_sum_equals_k/hafis/gpt35/jml/post.0.jml', # too complicated. we need to advance the prompt. `whose` is used
'./test/s0263_ugly_number/hafis/gpt35/jml/post.0.jml', # too complicated. we need to advance the prompt. `ugly number` is not defined
'./test/s0263_ugly_number/hafis/gpt35/jml/post.1.jml', # too complicated. we need to advance the prompt. `ugly number` is not defined
'./test/s0028_find_the_index_of_the_first_occurrence_in_a_string/hafis/gpt35/jml/post.0.jml', # ?
'./test/s0357_count_numbers_with_unique_digits/hafis/gpt35/jml/post.0.jml', # too complicated. we need to advance the prompt
'./test/s1021_remove_outermost_parentheses/hafis/gpt35/jml/post.0.jml', # too complicated. we need to advance the prompt
'./test/s1021_remove_outermost_parentheses/hafis/gpt35/jml/pre.1.jml', # missing information. valid parenthesis is not defined
'./test/s0405_convert_a_number_to_hexadecimal/hafis/gpt35/jml/post.0.jml', # future work. need runtime support to hold result to compare
'./test/s0415_add_strings/hafis/gpt35/jml/post.0.jml', # wrong information. sum is an arithmetic operation and cannot be applied to string type
'./test/s0415_add_strings/hafis/gpt35/jml/post.1.jml', # wrong information. a string to represent a string is nonesense
'./test/s0415_add_strings/hafis/gpt35/jml/post.2.jml', # future work. need runtime support in parsing string to integer
'./test/s0415_add_strings/hafis/gpt35/jml/pre.2.jml',
'./test/s0415_add_strings/hafis/gpt35/jml/pre.3.jml',
'./test/s0532_k_diff_pairs_in_an_array/hafis/gpt35/jml/post.1.jml', # missing information. k-diff needs the information of k
'./test/s0504_base_7/hafis/gpt35/jml/post.0.jml', # future work. base 7 representation needs runtime support to calculate
'./test/s0044_wildcard_matching/hafis/gpt35/jml/post.0.jml', # missing information. we don't have a type called pattern
'./test/s0044_wildcard_matching/hafis/gpt35/jml/post.1.jml', # missing information. we don't have a type called pattern
'./test/s0551_student_attendance_record_i/hafis/gpt35/jml/post.0.jml', # too complicated. we need to advance the prompt
'./test/s0551_student_attendance_record_i/hafis/gpt35/jml/post.1.jml', # too complicated. we need to advance the prompt
'./test/s0066_plus_one/hafis/gpt35/jml/post.0.jml', # future work. we need parsing support in runtime checking
'./test/s0072_edit_distance/hafis/gpt35/jml/post.0.jml', # future work. counting the number of operations needs runtime support.
'./test/s0961_n_repeated_element_in_size_2n_array/hafis/gpt35/jml/pre.2.jml', # missing information. unknown `n` is used
'./test/s0961_n_repeated_element_in_size_2n_array/hafis/gpt35/jml/pre.3.jml', # missing information. unknown `n` is used
'./test/s0326_power_of_three/hafis/gpt35/jml/post.0.jml', # ?
'./test/s0326_power_of_three/hafis/gpt35/jml/post.1.jml', # ?
'./test/s0454_4sum_ii/hafis/gpt35/jml/post.1.jml', # too complicated and requires runtime support to access tuples
'./test/s0454_4sum_ii/hafis/gpt35/jml/post.2.jml', # too complicated and requires runtime support to access tuples
'./test/s0561_array_partition_i/hafis/gpt35/jml/post.0.jml', # missing information. we don't know what is a pair in the input array
'./test/s0387_first_unique_character_in_a_string/hafis/gpt35/jml/post.0.jml', # missing information. `that` is used and we don't know which it is referring to. NLP or the prompt needs advancement
'./test/s0520_detect_capital/hafis/gpt35/jml/post.2.jml', # future work. we may infer the specification if it mentions none of the above
'./test/s0520_detect_capital/hafis/gpt35/jml/post.3.jml', # missing information
'./test/s0941_valid_mountain_array/hafis/gpt35/jml/post.0.jml', # missing information
'./test/s0941_valid_mountain_array/hafis/gpt35/jml/post.1.jml', # missing information
'./test/s0006_zigzag_conversion/hafis/gpt35/jml/post.0.jml', # missing information
'./test/s0006_zigzag_conversion/hafis/gpt35/jml/pre.0.jml', # ?
'./test/s0908_smallest_range_i/hafis/gpt35/jml/post.0.jml', # ?
'./test/s0860_lemonade_change/hafis/gpt35/jml/post.0.jml', # missing information
'./test/s0860_lemonade_change/hafis/gpt35/jml/post.1.jml', # missing information
'./test/s0342_power_of_four/hafis/gpt35/jml/post.0.jml', # too complicated. we need to advance the prompt and `there` is used
'./test/s0342_power_of_four/hafis/gpt35/jml/post.1.jml', # too complicated. we need to advance the prompt and `there` is used
'./test/s0412_fizz_buzz/hafis/gpt35/jml/post.0.jml', # ?
'./test/s0412_fizz_buzz/hafis/gpt35/jml/post.1.jml', # ?
'./test/s0412_fizz_buzz/hafis/gpt35/jml/post.2.jml', # missing information. index `i` is referred by can be rewritten into simpler sentence
'./test/s0412_fizz_buzz/hafis/gpt35/jml/post.3.jml', # missing information. index `i` is referred by can be rewritten into simpler sentence
'./test/s0412_fizz_buzz/hafis/gpt35/jml/post.4.jml', # missing information. index `i` is referred by can be rewritten into simpler sentence
'./test/s0053_maximum_subarray/hafis/gpt35/jml/post.0.jml', # need runtime support. largest subarray sum requires runtime temp storage
'./test/s0202_happy_number/hafis/gpt35/jml/post.0.jml', # missing information. `happy number` is not defined.
'./test/s0202_happy_number/hafis/gpt35/jml/post.1.jml', # missing information. `happy number` is not defined.
'./test/s0067_add_binary/hafis/gpt35/jml/post.0.jml', # too complicated. runtime datatype conversion is required.
'./test/s0058_length_of_last_word/hafis/gpt35/jml/post.0.jml', # missing information. word is not defined.
'./test/s0058_length_of_last_word/hafis/gpt35/jml/pre.2.jml', # missing information. word is not defined and `there` is used.
'./test/s0451_sort_characters_by_frequency/hafis/gpt35/jml/post.0.jml', # too complicated. we need to advance the prompt
'./test/s0451_sort_characters_by_frequency/hafis/gpt35/jml/post.1.jml', # too complicated. `there` is used.
'./test/s0228_summary_ranges/hafis/gpt35/jml/pre.3.jml', # missing information. `1 of the ranges` is requirement specific
'./test/s0299_bulls_and_cows/hafis/gpt35/jml/post.0.jml', # missing information. bull and cow are used without defined
'./test/s0134_gas_station/hafis/gpt35/jml/post.0.jml', # too complicated. we need to advance the prompt. `there` is used
'./test/s0134_gas_station/hafis/gpt35/jml/post.1.jml', # too complicated. we need to advance the prompt. 
'./test/s0134_gas_station/hafis/gpt35/jml/post.2.jml', # missing information. `arrays` are used without parameter. not fulfilling the grammar
'./test/s0134_gas_station/hafis/gpt35/jml/post.3.jml', # missing information. `arrays` are used without parameter. not fulfilling the grammar
'./test/s0393_utf_8_validation/hafis/gpt35/jml/post.0.jml', # missing information. utf-encoding is not defined. integer to char parsing is required
'./test/s0393_utf_8_validation/hafis/gpt35/jml/post.1.jml', # missing information. utf-encoding is not defined. integer to char parsing is required
'./test/s0231_power_of_two/hafis/gpt35/jml/post.0.jml', # too complicated. we need to advance the prompt. `there` is used
'./test/s0231_power_of_two/hafis/gpt35/jml/post.1.jml', # too complicated. we need to advance the prompt. `there` is used
'./test/s0035_search_insert_position/hafis/gpt35/jml/post.0.jml', # too complicated. we need to advance the prompt
'./test/s0035_search_insert_position/hafis/gpt35/jml/post.1.jml', # too complicated. we need to advance the prompt
'./test/s0292_nim_game/hafis/gpt35/jml/post.0.jml', # too complicated. we need to advance the prompt
'./test/s0292_nim_game/hafis/gpt35/jml/post.1.jml', # too complicated. we need to advance the prompt
'./test/s0091_decode_ways/hafis/gpt35/jml/pre.0.jml', # wrong datatype. string is being compared with integer
'./test/s0454_4sum_ii/hafis/gpt35/jml/pre.0.jml',   # wrong datatype. integer is being compared with a character, although it is comparable, but the semantic is length of integer array compared with a character
'./test/s0454_4sum_ii/hafis/gpt35/jml/pre.1.jml',   # wrong datatype. integer is being compared with a character, although it is comparable, but the semantic is length of integer array compared with a character
'./test/s0454_4sum_ii/hafis/gpt35/jml/pre.2.jml',   # wrong datatype. integer is being compared with a character, although it is comparable, but the semantic is length of integer array compared with a character
'./test/s0454_4sum_ii/hafis/gpt35/jml/pre.3.jml',   # wrong datatype. integer is being compared with a character, although it is comparable, but the semantic is length of integer array compared with a character
'./test/s0152_maximum_product_subarray/hafis/gpt35/jml/pre.0.jml', # wrong datatype. integer array is being compared with integer
'./test/s0053_maximum_subarray/hafis/gpt35/jml/pre.0.jml', # wrong datatype. integer array is being compared with integer
    ],
    'gpt4': [
        './test/s0020_valid_parentheses/hafis/gpt4/jml/post.0.jml',     # use of it 
        './test/s0020_valid_parentheses/hafis/gpt4/jml/post.1.jml',     # too complicated. we need to advance the prompt
        './test/s0989_add_to_array_form_of_integer/hafis/gpt4/jml/post.2.jml', # too complicated. we need to advance the prompt. requires runtime parsing from list to integer
        './test/s0989_add_to_array_form_of_integer/hafis/gpt4/jml/pre.3.jml',   # the symbol array is missing and array is not a parameter name
        './test/s0191_number_of_1_bits/hafis/gpt4/jml/pre.0.jml',       # the sentence asks for the changed version of parameter without mentioning result
        './test/s0238_product_of_array_except_self/hafis/gpt4/jml/post.3.jml',  # need JML advancement. asking for using the index of the same element to do the product
        './test/s0151_reverse_words_in_a_string/hafis/gpt4/jml/post.0.jml', # missing information. definition of word is unknown and it is requirement specific
        './test/s0151_reverse_words_in_a_string/hafis/gpt4/jml/post.1.jml',# missing information. definition of word is unknown and it is requirement specific
        './test/s0151_reverse_words_in_a_string/hafis/gpt4/jml/pre.2.jml', # missing information. definition of word is unknown and it is requirement specific
        './test/s0229_majority_element_ii/hafis/gpt4/jml/post.0.jml', # too complicated. we need to advance the prompt
        './test/s0001_two_sum/hafis/gpt4/jml/post.1.jml',   # too complicated. we need to advance the prompt
        './test/s0001_two_sum/hafis/gpt4/jml/post.2.jml',   # missing information. definition of indices is missing and we don't know what indices are referring to
        './test/s0227_basic_calculator_ii/hafis/gpt4/jml/pre.1.jml', # too complicated. we need to advance the prompt
        './test/s0227_basic_calculator_ii/hafis/gpt4/jml/pre.2.jml', # too complicated. we need to advance the prompt. as a single spec, integer and string types are not compatible
        './test/s0331_verify_preorder_serialization_of_a_binary_tree/hafis/gpt4/jml/post.0.jml',# too complicated. we need to advance the prompt. we don't know what is valid serialization of tree preorder traversal
        './test/s0331_verify_preorder_serialization_of_a_binary_tree/hafis/gpt4/jml/post.1.jml',# too complicated. we need to advance the prompt. we don't know what is valid serialization of tree preorder traversal
        './test/s0331_verify_preorder_serialization_of_a_binary_tree/hafis/gpt4/jml/pre.1.jml', # ?
        './test/s0628_maximum_product_of_three_numbers/hafis/gpt4/jml/post.0.jml', # too complicated. we need to advance the prompt. `any 3 unique elements` is too complicated
        './test/s0521_longest_uncommon_subsequence_i/hafis/gpt4/jml/post.0.jml', # too complicated. we need to advance the prompt. `maximum length between two elements in an array` is too complicated
        './test/s1013_partition_array_into_three_parts_with_equal_sum/hafis/gpt4/jml/post.0.jml', # too complicated. we need to advance the prompt. partitioning an array into three parts with equal sum requires runtime support
        './test/s1013_partition_array_into_three_parts_with_equal_sum/hafis/gpt4/jml/post.1.jml', # too complicated. we need to advance the prompt. partitioning an array into three parts with equal sum requires runtime support
        './test/s0263_ugly_number/hafis/gpt4/jml/post.0.jml', # ?
        './test/s0263_ugly_number/hafis/gpt4/jml/post.1.jml', # ?
        './test/s0033_search_in_rotated_sorted_array/hafis/gpt4/jml/post.3.jml', # too complicated. we need to advance the prompt
        './test/s1021_remove_outermost_parentheses/hafis/gpt4/jml/post.0.jml', # missing information. valid parenthesis is not defined
        './test/s1021_remove_outermost_parentheses/hafis/gpt4/jml/pre.2.jml', # missing information. valid parenthesis is not defined
        './test/s0165_compare_version_numbers/hafis/gpt4/jml/pre.4.jml', # missing information. revision is not defined
        './test/s0165_compare_version_numbers/hafis/gpt4/jml/pre.5.jml', # missing information. revision is not defined
        './test/s0415_add_strings/hafis/gpt4/jml/post.1.jml', # too complicated. we need to advance the prompt
        './test/s0007_reverse_integer/hafis/gpt4/jml/post.1.jml', # exception is out of scope
        './test/s0504_base_7/hafis/gpt4/jml/post.0.jml',    # need runtime support for parsing int to string
        './test/s0551_student_attendance_record_i/hafis/gpt4/jml/post.0.jml', # ?
        './test/s0551_student_attendance_record_i/hafis/gpt4/jml/post.2.jml', # ?
        './test/s0961_n_repeated_element_in_size_2n_array/hafis/gpt4/jml/pre.0.jml', # missing information. unknown variable not declared in the program is used to specify integer comparison value
        './test/s0961_n_repeated_element_in_size_2n_array/hafis/gpt4/jml/pre.1.jml', # missing information. unknown variable not declared in the program is used to specify integer comparison value
        './test/s0961_n_repeated_element_in_size_2n_array/hafis/gpt4/jml/pre.2.jml', # missing information. unknown variable not declared in the program is used to specify integer comparison value
        './test/s0326_power_of_three/hafis/gpt4/jml/post.0.jml', # too complicated. we need to advance the prompt. using there
        './test/s0326_power_of_three/hafis/gpt4/jml/post.1.jml', # too complicated. we need to advance the prompt. using there
        './test/s0520_detect_capital/hafis/gpt4/jml/post.0.jml', # ?
        './test/s0520_detect_capital/hafis/gpt4/jml/post.1.jml', # missing information. correct capital is not defined.
        './test/s0941_valid_mountain_array/hafis/gpt4/jml/post.0.jml', # too complicated. we need to advance the prompt
        './test/s0941_valid_mountain_array/hafis/gpt4/jml/post.1.jml', # missing information. valid mountain is not defined
        './test/s0860_lemonade_change/hafis/gpt4/jml/post.0.jml', # missing information. correct change is not defined
        './test/s0860_lemonade_change/hafis/gpt4/jml/post.1.jml', # missing information. correct change is not defined
        './test/s0342_power_of_four/hafis/gpt4/jml/post.0.jml', # missing information. power to an unknown predicate
        './test/s0342_power_of_four/hafis/gpt4/jml/post.1.jml', # missing information. power to an unknown predicate
        './test/s0412_fizz_buzz/hafis/gpt4/jml/post.1.jml', # too complicated. index of an item has no information that the index is associated to
        './test/s0412_fizz_buzz/hafis/gpt4/jml/post.2.jml', # too complicated. index of an item has no information that the index is associated to
        './test/s0412_fizz_buzz/hafis/gpt4/jml/post.3.jml', # too complicated. index of an item has no information that the index is associated to
        './test/s0412_fizz_buzz/hafis/gpt4/jml/post.4.jml', # too complicated. index of an item has no information that the index is associated to
        './test/s0202_happy_number/hafis/gpt4/jml/post.0.jml',  # missing information. definition of happy number is missing
        './test/s0202_happy_number/hafis/gpt4/jml/post.1.jml',  # missing information. definition of happy number is missing
        './test/s0067_add_binary/hafis/gpt4/jml/post.1.jml', # too complicated. we need to advance the prompt
        './test/s0067_add_binary/hafis/gpt4/jml/pre.4.jml', # ?
        './test/s0067_add_binary/hafis/gpt4/jml/pre.5.jml', # ?
        './test/s0058_length_of_last_word/hafis/gpt4/jml/pre.2.jml', # missing information. definition of word is unknown and it is requirement specific
        './test/s0451_sort_characters_by_frequency/hafis/gpt4/jml/post.0.jml', # too complicated. we need to advance the prompt
        './test/s0228_summary_ranges/hafis/gpt4/jml/post.0.jml',  # too complicated. we need to advance the prompt
        './test/s0299_bulls_and_cows/hafis/gpt4/jml/post.0.jml',   # missing information. definition of bulls and cows is missing
        './test/s0942_di_string_match/hafis/gpt4/jml/post.1.jml',  # ?
        './test/s0393_utf_8_validation/hafis/gpt4/jml/post.0.jml', # need JML advancement. checking UTF-8 needs to have encoder JML helper function
        './test/s0393_utf_8_validation/hafis/gpt4/jml/post.1.jml', # need JML advancement. checking UTF-8 needs to have encoder JML helper function
        './test/s0231_power_of_two/hafis/gpt4/jml/post.0.jml',# too complicated. we need to advance the prompt. `there` is used and missing information of `n`
        './test/s0231_power_of_two/hafis/gpt4/jml/post.1.jml', # too complicated. we need to advance the prompt. `there` is used and missing information of `n`
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
        temp = "%s.%s.jml"
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
                data.append(os.path.join(folder, model, "jml", temp % (head, str(count))))
                count += 1
        for file in glob(os.path.join(folder, model, "jml/*.jml")):
            with open(file, "r") as fp:
                _tmp = fp.read()
                if _tmp and _tmp.strip() == "Failed":
                    failed.append(file)
                elif _tmp and '() ==>' in _tmp.strip():
                    incomplete.append(file)
            data.remove(file)
    return data, failed, incomplete

# for model in ["starchat", "gpt35", "gpt4"]:
for model in ["gpt35"]:
    data, failed, incomplete = calc(model)
    if data:
        unexpected = []
        print("%s Missing:" % model)
        c = 0
        for i in data:
            if i in expected_wrong[model]:
                print(i, " --> Failed as expected")
            else:
                c += 1
                print(i)
                unexpected.append(i)
        # print("\n".join(data))
        print("unexpected count: ", c)
        print("expected count: ", len(data))
        if unexpected:
            [print("%s" % i) for i in unexpected]

    if failed:
        print("%s Failed " % model)
        for i in failed:
            if i in expected_wrong[model]:
                print(i, " --> Failed as expected")
            else:
                print(i)
        print("count: ", len(failed))

    if incomplete:
        print("%s Incomplete JML" % model)
        for i in incomplete:
            if i in expected_wrong[model]:
                print(i, " --> Failed as expected")
            else:
                print(i)
        print("count: ", len(incomplete))