import Management.Input;

public class Inputs {
    public static final int phase = 2;

    // Sample data
    public static final String sampleOverride = "";
    public static final String sampleData = "Button A: X+94, Y+34\n" +
            "Button B: X+22, Y+67\n" +
            "Prize: X=8400, Y=5400\n" +
            "\n" +
            "Button A: X+26, Y+66\n" +
            "Button B: X+67, Y+21\n" +
            "Prize: X=12748, Y=12176\n" +
            "\n" +
            "Button A: X+17, Y+86\n" +
            "Button B: X+84, Y+37\n" +
            "Prize: X=7870, Y=6450\n" +
            "\n" +
            "Button A: X+69, Y+23\n" +
            "Button B: X+27, Y+71\n" +
            "Prize: X=18641, Y=10279";
    public static final Long sampleResult1 = 480L;
    public static final Long sampleResult2 = null;
    // Puzzle data
    public static final long[] tooLow  = new long[] {};
    public static final long[] tooHigh = new long[] {};
    public static final String trueData = "Button A: X+23, Y+97\n" +
            "Button B: X+93, Y+12\n" +
            "Prize: X=6993, Y=2877\n" +
            "\n" +
            "Button A: X+69, Y+13\n" +
            "Button B: X+16, Y+78\n" +
            "Prize: X=16314, Y=9324\n" +
            "\n" +
            "Button A: X+16, Y+43\n" +
            "Button B: X+40, Y+12\n" +
            "Prize: X=17664, Y=2838\n" +
            "\n" +
            "Button A: X+73, Y+29\n" +
            "Button B: X+24, Y+94\n" +
            "Prize: X=1335, Y=1375\n" +
            "\n" +
            "Button A: X+97, Y+37\n" +
            "Button B: X+32, Y+54\n" +
            "Prize: X=6457, Y=3675\n" +
            "\n" +
            "Button A: X+33, Y+97\n" +
            "Button B: X+89, Y+71\n" +
            "Prize: X=6726, Y=8334\n" +
            "\n" +
            "Button A: X+58, Y+34\n" +
            "Button B: X+13, Y+43\n" +
            "Prize: X=9979, Y=1021\n" +
            "\n" +
            "Button A: X+16, Y+51\n" +
            "Button B: X+66, Y+37\n" +
            "Prize: X=19072, Y=5135\n" +
            "\n" +
            "Button A: X+18, Y+70\n" +
            "Button B: X+54, Y+53\n" +
            "Prize: X=1980, Y=5816\n" +
            "\n" +
            "Button A: X+97, Y+26\n" +
            "Button B: X+49, Y+59\n" +
            "Prize: X=9823, Y=5018\n" +
            "\n" +
            "Button A: X+57, Y+15\n" +
            "Button B: X+12, Y+71\n" +
            "Prize: X=2807, Y=17918\n" +
            "\n" +
            "Button A: X+71, Y+69\n" +
            "Button B: X+11, Y+70\n" +
            "Prize: X=6976, Y=12236\n" +
            "\n" +
            "Button A: X+65, Y+71\n" +
            "Button B: X+54, Y+13\n" +
            "Prize: X=8680, Y=7182\n" +
            "\n" +
            "Button A: X+47, Y+91\n" +
            "Button B: X+27, Y+16\n" +
            "Prize: X=2812, Y=4066\n" +
            "\n" +
            "Button A: X+44, Y+19\n" +
            "Button B: X+24, Y+55\n" +
            "Prize: X=17644, Y=14291\n" +
            "\n" +
            "Button A: X+35, Y+17\n" +
            "Button B: X+15, Y+32\n" +
            "Prize: X=9210, Y=16679\n" +
            "\n" +
            "Button A: X+30, Y+78\n" +
            "Button B: X+87, Y+20\n" +
            "Prize: X=6375, Y=7296\n" +
            "\n" +
            "Button A: X+20, Y+49\n" +
            "Button B: X+85, Y+25\n" +
            "Prize: X=6480, Y=3415\n" +
            "\n" +
            "Button A: X+77, Y+56\n" +
            "Button B: X+30, Y+72\n" +
            "Prize: X=2785, Y=2176\n" +
            "\n" +
            "Button A: X+75, Y+24\n" +
            "Button B: X+13, Y+41\n" +
            "Prize: X=7774, Y=17595\n" +
            "\n" +
            "Button A: X+92, Y+31\n" +
            "Button B: X+29, Y+90\n" +
            "Prize: X=1617, Y=946\n" +
            "\n" +
            "Button A: X+67, Y+22\n" +
            "Button B: X+32, Y+49\n" +
            "Prize: X=2424, Y=3067\n" +
            "\n" +
            "Button A: X+45, Y+17\n" +
            "Button B: X+35, Y+76\n" +
            "Prize: X=1770, Y=2552\n" +
            "\n" +
            "Button A: X+59, Y+30\n" +
            "Button B: X+42, Y+85\n" +
            "Prize: X=3884, Y=2675\n" +
            "\n" +
            "Button A: X+16, Y+80\n" +
            "Button B: X+98, Y+43\n" +
            "Prize: X=10772, Y=10054\n" +
            "\n" +
            "Button A: X+44, Y+16\n" +
            "Button B: X+51, Y+75\n" +
            "Prize: X=2188, Y=2828\n" +
            "\n" +
            "Button A: X+44, Y+20\n" +
            "Button B: X+36, Y+62\n" +
            "Prize: X=9480, Y=9028\n" +
            "\n" +
            "Button A: X+61, Y+25\n" +
            "Button B: X+27, Y+57\n" +
            "Prize: X=12743, Y=7217\n" +
            "\n" +
            "Button A: X+56, Y+25\n" +
            "Button B: X+17, Y+49\n" +
            "Prize: X=7578, Y=19690\n" +
            "\n" +
            "Button A: X+80, Y+94\n" +
            "Button B: X+75, Y+14\n" +
            "Prize: X=9665, Y=5204\n" +
            "\n" +
            "Button A: X+67, Y+83\n" +
            "Button B: X+81, Y+18\n" +
            "Prize: X=9957, Y=8547\n" +
            "\n" +
            "Button A: X+46, Y+11\n" +
            "Button B: X+15, Y+76\n" +
            "Prize: X=4545, Y=8108\n" +
            "\n" +
            "Button A: X+91, Y+28\n" +
            "Button B: X+41, Y+94\n" +
            "Prize: X=6589, Y=3004\n" +
            "\n" +
            "Button A: X+76, Y+21\n" +
            "Button B: X+19, Y+73\n" +
            "Prize: X=10171, Y=557\n" +
            "\n" +
            "Button A: X+47, Y+77\n" +
            "Button B: X+56, Y+28\n" +
            "Prize: X=10029, Y=10311\n" +
            "\n" +
            "Button A: X+18, Y+72\n" +
            "Button B: X+95, Y+17\n" +
            "Prize: X=9330, Y=6828\n" +
            "\n" +
            "Button A: X+73, Y+46\n" +
            "Button B: X+11, Y+27\n" +
            "Prize: X=7962, Y=2209\n" +
            "\n" +
            "Button A: X+40, Y+23\n" +
            "Button B: X+20, Y+52\n" +
            "Prize: X=16860, Y=16258\n" +
            "\n" +
            "Button A: X+66, Y+84\n" +
            "Button B: X+94, Y+24\n" +
            "Prize: X=7066, Y=4020\n" +
            "\n" +
            "Button A: X+85, Y+13\n" +
            "Button B: X+63, Y+95\n" +
            "Prize: X=9169, Y=5073\n" +
            "\n" +
            "Button A: X+22, Y+41\n" +
            "Button B: X+48, Y+13\n" +
            "Prize: X=19334, Y=16827\n" +
            "\n" +
            "Button A: X+88, Y+46\n" +
            "Button B: X+13, Y+82\n" +
            "Prize: X=3391, Y=5608\n" +
            "\n" +
            "Button A: X+26, Y+36\n" +
            "Button B: X+90, Y+33\n" +
            "Prize: X=8592, Y=3468\n" +
            "\n" +
            "Button A: X+29, Y+69\n" +
            "Button B: X+85, Y+52\n" +
            "Prize: X=3490, Y=4698\n" +
            "\n" +
            "Button A: X+24, Y+11\n" +
            "Button B: X+14, Y+44\n" +
            "Prize: X=990, Y=5512\n" +
            "\n" +
            "Button A: X+30, Y+55\n" +
            "Button B: X+54, Y+16\n" +
            "Prize: X=14540, Y=985\n" +
            "\n" +
            "Button A: X+27, Y+35\n" +
            "Button B: X+82, Y+33\n" +
            "Prize: X=1877, Y=1407\n" +
            "\n" +
            "Button A: X+81, Y+81\n" +
            "Button B: X+14, Y+63\n" +
            "Prize: X=4187, Y=4383\n" +
            "\n" +
            "Button A: X+37, Y+19\n" +
            "Button B: X+14, Y+47\n" +
            "Prize: X=2399, Y=2903\n" +
            "\n" +
            "Button A: X+15, Y+83\n" +
            "Button B: X+58, Y+64\n" +
            "Prize: X=3905, Y=4907\n" +
            "\n" +
            "Button A: X+14, Y+63\n" +
            "Button B: X+54, Y+15\n" +
            "Prize: X=5384, Y=2984\n" +
            "\n" +
            "Button A: X+11, Y+47\n" +
            "Button B: X+71, Y+36\n" +
            "Prize: X=13234, Y=17421\n" +
            "\n" +
            "Button A: X+55, Y+11\n" +
            "Button B: X+32, Y+71\n" +
            "Prize: X=12525, Y=11037\n" +
            "\n" +
            "Button A: X+17, Y+80\n" +
            "Button B: X+63, Y+45\n" +
            "Prize: X=2242, Y=2755\n" +
            "\n" +
            "Button A: X+18, Y+69\n" +
            "Button B: X+72, Y+27\n" +
            "Prize: X=12266, Y=16259\n" +
            "\n" +
            "Button A: X+97, Y+31\n" +
            "Button B: X+76, Y+84\n" +
            "Prize: X=4037, Y=1947\n" +
            "\n" +
            "Button A: X+12, Y+44\n" +
            "Button B: X+41, Y+37\n" +
            "Prize: X=2697, Y=3429\n" +
            "\n" +
            "Button A: X+19, Y+88\n" +
            "Button B: X+83, Y+34\n" +
            "Prize: X=5511, Y=8354\n" +
            "\n" +
            "Button A: X+17, Y+22\n" +
            "Button B: X+83, Y+17\n" +
            "Prize: X=9145, Y=3336\n" +
            "\n" +
            "Button A: X+24, Y+85\n" +
            "Button B: X+72, Y+38\n" +
            "Prize: X=1032, Y=1051\n" +
            "\n" +
            "Button A: X+18, Y+40\n" +
            "Button B: X+48, Y+16\n" +
            "Prize: X=12434, Y=6744\n" +
            "\n" +
            "Button A: X+47, Y+13\n" +
            "Button B: X+26, Y+74\n" +
            "Prize: X=4399, Y=11701\n" +
            "\n" +
            "Button A: X+19, Y+91\n" +
            "Button B: X+51, Y+37\n" +
            "Prize: X=4876, Y=8016\n" +
            "\n" +
            "Button A: X+68, Y+37\n" +
            "Button B: X+24, Y+46\n" +
            "Prize: X=3276, Y=3199\n" +
            "\n" +
            "Button A: X+13, Y+29\n" +
            "Button B: X+57, Y+39\n" +
            "Prize: X=5788, Y=5544\n" +
            "\n" +
            "Button A: X+24, Y+53\n" +
            "Button B: X+68, Y+39\n" +
            "Prize: X=18740, Y=14361\n" +
            "\n" +
            "Button A: X+51, Y+18\n" +
            "Button B: X+23, Y+72\n" +
            "Prize: X=17447, Y=17126\n" +
            "\n" +
            "Button A: X+39, Y+19\n" +
            "Button B: X+30, Y+60\n" +
            "Prize: X=3491, Y=10211\n" +
            "\n" +
            "Button A: X+85, Y+24\n" +
            "Button B: X+23, Y+94\n" +
            "Prize: X=2812, Y=8582\n" +
            "\n" +
            "Button A: X+29, Y+69\n" +
            "Button B: X+58, Y+21\n" +
            "Prize: X=10443, Y=4862\n" +
            "\n" +
            "Button A: X+27, Y+44\n" +
            "Button B: X+95, Y+31\n" +
            "Prize: X=894, Y=714\n" +
            "\n" +
            "Button A: X+87, Y+54\n" +
            "Button B: X+48, Y+99\n" +
            "Prize: X=8262, Y=5751\n" +
            "\n" +
            "Button A: X+23, Y+70\n" +
            "Button B: X+44, Y+12\n" +
            "Prize: X=4080, Y=5956\n" +
            "\n" +
            "Button A: X+12, Y+52\n" +
            "Button B: X+82, Y+23\n" +
            "Prize: X=15086, Y=14567\n" +
            "\n" +
            "Button A: X+37, Y+12\n" +
            "Button B: X+16, Y+43\n" +
            "Prize: X=14856, Y=930\n" +
            "\n" +
            "Button A: X+11, Y+77\n" +
            "Button B: X+69, Y+16\n" +
            "Prize: X=13006, Y=5385\n" +
            "\n" +
            "Button A: X+23, Y+51\n" +
            "Button B: X+41, Y+11\n" +
            "Prize: X=8141, Y=11813\n" +
            "\n" +
            "Button A: X+56, Y+12\n" +
            "Button B: X+37, Y+84\n" +
            "Prize: X=5392, Y=10244\n" +
            "\n" +
            "Button A: X+41, Y+12\n" +
            "Button B: X+21, Y+97\n" +
            "Prize: X=2070, Y=4240\n" +
            "\n" +
            "Button A: X+55, Y+97\n" +
            "Button B: X+77, Y+31\n" +
            "Prize: X=11418, Y=10286\n" +
            "\n" +
            "Button A: X+11, Y+52\n" +
            "Button B: X+64, Y+14\n" +
            "Prize: X=1788, Y=18898\n" +
            "\n" +
            "Button A: X+64, Y+13\n" +
            "Button B: X+18, Y+98\n" +
            "Prize: X=4300, Y=2949\n" +
            "\n" +
            "Button A: X+31, Y+70\n" +
            "Button B: X+68, Y+38\n" +
            "Prize: X=917, Y=1724\n" +
            "\n" +
            "Button A: X+12, Y+22\n" +
            "Button B: X+96, Y+38\n" +
            "Prize: X=10188, Y=5706\n" +
            "\n" +
            "Button A: X+49, Y+11\n" +
            "Button B: X+12, Y+62\n" +
            "Prize: X=15408, Y=11956\n" +
            "\n" +
            "Button A: X+71, Y+56\n" +
            "Button B: X+25, Y+91\n" +
            "Prize: X=1469, Y=2513\n" +
            "\n" +
            "Button A: X+15, Y+47\n" +
            "Button B: X+66, Y+12\n" +
            "Prize: X=2153, Y=10113\n" +
            "\n" +
            "Button A: X+13, Y+62\n" +
            "Button B: X+79, Y+85\n" +
            "Prize: X=6430, Y=9367\n" +
            "\n" +
            "Button A: X+18, Y+77\n" +
            "Button B: X+44, Y+12\n" +
            "Prize: X=10844, Y=2702\n" +
            "\n" +
            "Button A: X+93, Y+35\n" +
            "Button B: X+26, Y+58\n" +
            "Prize: X=8896, Y=6048\n" +
            "\n" +
            "Button A: X+39, Y+38\n" +
            "Button B: X+55, Y+12\n" +
            "Prize: X=3970, Y=3078\n" +
            "\n" +
            "Button A: X+74, Y+74\n" +
            "Button B: X+91, Y+21\n" +
            "Prize: X=11192, Y=6852\n" +
            "\n" +
            "Button A: X+60, Y+39\n" +
            "Button B: X+32, Y+65\n" +
            "Prize: X=4848, Y=4212\n" +
            "\n" +
            "Button A: X+85, Y+64\n" +
            "Button B: X+11, Y+40\n" +
            "Prize: X=5294, Y=6016\n" +
            "\n" +
            "Button A: X+50, Y+91\n" +
            "Button B: X+65, Y+14\n" +
            "Prize: X=10540, Y=10213\n" +
            "\n" +
            "Button A: X+76, Y+22\n" +
            "Button B: X+11, Y+66\n" +
            "Prize: X=7809, Y=3840\n" +
            "\n" +
            "Button A: X+21, Y+41\n" +
            "Button B: X+40, Y+18\n" +
            "Prize: X=14542, Y=14586\n" +
            "\n" +
            "Button A: X+92, Y+71\n" +
            "Button B: X+24, Y+85\n" +
            "Prize: X=3776, Y=6238\n" +
            "\n" +
            "Button A: X+56, Y+87\n" +
            "Button B: X+86, Y+23\n" +
            "Prize: X=7110, Y=3414\n" +
            "\n" +
            "Button A: X+92, Y+42\n" +
            "Button B: X+19, Y+81\n" +
            "Prize: X=701, Y=537\n" +
            "\n" +
            "Button A: X+26, Y+59\n" +
            "Button B: X+56, Y+21\n" +
            "Prize: X=2004, Y=3633\n" +
            "\n" +
            "Button A: X+77, Y+11\n" +
            "Button B: X+12, Y+57\n" +
            "Prize: X=15477, Y=3321\n" +
            "\n" +
            "Button A: X+41, Y+16\n" +
            "Button B: X+23, Y+68\n" +
            "Prize: X=1951, Y=9676\n" +
            "\n" +
            "Button A: X+51, Y+89\n" +
            "Button B: X+89, Y+29\n" +
            "Prize: X=2824, Y=1644\n" +
            "\n" +
            "Button A: X+15, Y+47\n" +
            "Button B: X+41, Y+13\n" +
            "Prize: X=16130, Y=3046\n" +
            "\n" +
            "Button A: X+83, Y+19\n" +
            "Button B: X+94, Y+92\n" +
            "Prize: X=7904, Y=5122\n" +
            "\n" +
            "Button A: X+31, Y+91\n" +
            "Button B: X+51, Y+14\n" +
            "Prize: X=2361, Y=5845\n" +
            "\n" +
            "Button A: X+50, Y+46\n" +
            "Button B: X+15, Y+55\n" +
            "Prize: X=2960, Y=2888\n" +
            "\n" +
            "Button A: X+21, Y+58\n" +
            "Button B: X+68, Y+35\n" +
            "Prize: X=2525, Y=8683\n" +
            "\n" +
            "Button A: X+33, Y+18\n" +
            "Button B: X+19, Y+50\n" +
            "Prize: X=11199, Y=13498\n" +
            "\n" +
            "Button A: X+65, Y+27\n" +
            "Button B: X+32, Y+70\n" +
            "Prize: X=305, Y=4371\n" +
            "\n" +
            "Button A: X+63, Y+17\n" +
            "Button B: X+12, Y+63\n" +
            "Prize: X=12848, Y=11817\n" +
            "\n" +
            "Button A: X+64, Y+21\n" +
            "Button B: X+15, Y+38\n" +
            "Prize: X=15433, Y=14539\n" +
            "\n" +
            "Button A: X+23, Y+38\n" +
            "Button B: X+95, Y+34\n" +
            "Prize: X=5009, Y=4710\n" +
            "\n" +
            "Button A: X+21, Y+43\n" +
            "Button B: X+48, Y+22\n" +
            "Prize: X=5945, Y=5653\n" +
            "\n" +
            "Button A: X+36, Y+66\n" +
            "Button B: X+45, Y+19\n" +
            "Prize: X=9548, Y=6616\n" +
            "\n" +
            "Button A: X+11, Y+88\n" +
            "Button B: X+18, Y+11\n" +
            "Prize: X=2567, Y=7502\n" +
            "\n" +
            "Button A: X+63, Y+15\n" +
            "Button B: X+18, Y+79\n" +
            "Prize: X=9071, Y=2005\n" +
            "\n" +
            "Button A: X+96, Y+20\n" +
            "Button B: X+21, Y+34\n" +
            "Prize: X=8250, Y=3200\n" +
            "\n" +
            "Button A: X+16, Y+53\n" +
            "Button B: X+63, Y+49\n" +
            "Prize: X=3317, Y=4121\n" +
            "\n" +
            "Button A: X+14, Y+44\n" +
            "Button B: X+62, Y+39\n" +
            "Prize: X=2814, Y=3389\n" +
            "\n" +
            "Button A: X+27, Y+11\n" +
            "Button B: X+25, Y+66\n" +
            "Prize: X=9498, Y=760\n" +
            "\n" +
            "Button A: X+49, Y+18\n" +
            "Button B: X+19, Y+47\n" +
            "Prize: X=13964, Y=7779\n" +
            "\n" +
            "Button A: X+63, Y+38\n" +
            "Button B: X+17, Y+50\n" +
            "Prize: X=3851, Y=3078\n" +
            "\n" +
            "Button A: X+13, Y+73\n" +
            "Button B: X+58, Y+21\n" +
            "Prize: X=3753, Y=6754\n" +
            "\n" +
            "Button A: X+62, Y+62\n" +
            "Button B: X+12, Y+89\n" +
            "Prize: X=1748, Y=8986\n" +
            "\n" +
            "Button A: X+58, Y+30\n" +
            "Button B: X+18, Y+52\n" +
            "Prize: X=7412, Y=10326\n" +
            "\n" +
            "Button A: X+51, Y+15\n" +
            "Button B: X+33, Y+79\n" +
            "Prize: X=2351, Y=18625\n" +
            "\n" +
            "Button A: X+41, Y+12\n" +
            "Button B: X+14, Y+63\n" +
            "Prize: X=6251, Y=9782\n" +
            "\n" +
            "Button A: X+71, Y+41\n" +
            "Button B: X+11, Y+98\n" +
            "Prize: X=4898, Y=8144\n" +
            "\n" +
            "Button A: X+25, Y+11\n" +
            "Button B: X+37, Y+68\n" +
            "Prize: X=13265, Y=13700\n" +
            "\n" +
            "Button A: X+16, Y+87\n" +
            "Button B: X+34, Y+23\n" +
            "Prize: X=1622, Y=1859\n" +
            "\n" +
            "Button A: X+45, Y+14\n" +
            "Button B: X+21, Y+65\n" +
            "Prize: X=6593, Y=8788\n" +
            "\n" +
            "Button A: X+12, Y+34\n" +
            "Button B: X+56, Y+20\n" +
            "Prize: X=14292, Y=10894\n" +
            "\n" +
            "Button A: X+97, Y+71\n" +
            "Button B: X+32, Y+74\n" +
            "Prize: X=7508, Y=9390\n" +
            "\n" +
            "Button A: X+91, Y+94\n" +
            "Button B: X+99, Y+26\n" +
            "Prize: X=10533, Y=6762\n" +
            "\n" +
            "Button A: X+91, Y+79\n" +
            "Button B: X+77, Y+14\n" +
            "Prize: X=13720, Y=8053\n" +
            "\n" +
            "Button A: X+97, Y+65\n" +
            "Button B: X+35, Y+73\n" +
            "Prize: X=10273, Y=8519\n" +
            "\n" +
            "Button A: X+52, Y+56\n" +
            "Button B: X+12, Y+60\n" +
            "Prize: X=3076, Y=5384\n" +
            "\n" +
            "Button A: X+24, Y+78\n" +
            "Button B: X+87, Y+80\n" +
            "Prize: X=1914, Y=5004\n" +
            "\n" +
            "Button A: X+25, Y+64\n" +
            "Button B: X+86, Y+40\n" +
            "Prize: X=7861, Y=6432\n" +
            "\n" +
            "Button A: X+76, Y+18\n" +
            "Button B: X+11, Y+67\n" +
            "Prize: X=4187, Y=6341\n" +
            "\n" +
            "Button A: X+16, Y+54\n" +
            "Button B: X+73, Y+11\n" +
            "Prize: X=11779, Y=14913\n" +
            "\n" +
            "Button A: X+61, Y+11\n" +
            "Button B: X+71, Y+61\n" +
            "Prize: X=6710, Y=4150\n" +
            "\n" +
            "Button A: X+13, Y+23\n" +
            "Button B: X+45, Y+18\n" +
            "Prize: X=10234, Y=5208\n" +
            "\n" +
            "Button A: X+33, Y+14\n" +
            "Button B: X+37, Y+70\n" +
            "Prize: X=9190, Y=4372\n" +
            "\n" +
            "Button A: X+55, Y+84\n" +
            "Button B: X+91, Y+30\n" +
            "Prize: X=8397, Y=3888\n" +
            "\n" +
            "Button A: X+54, Y+27\n" +
            "Button B: X+40, Y+69\n" +
            "Prize: X=13734, Y=4685\n" +
            "\n" +
            "Button A: X+56, Y+42\n" +
            "Button B: X+38, Y+96\n" +
            "Prize: X=3582, Y=4374\n" +
            "\n" +
            "Button A: X+68, Y+28\n" +
            "Button B: X+62, Y+94\n" +
            "Prize: X=5090, Y=5314\n" +
            "\n" +
            "Button A: X+79, Y+42\n" +
            "Button B: X+33, Y+87\n" +
            "Prize: X=1238, Y=936\n" +
            "\n" +
            "Button A: X+13, Y+73\n" +
            "Button B: X+76, Y+53\n" +
            "Prize: X=2245, Y=2141\n" +
            "\n" +
            "Button A: X+98, Y+43\n" +
            "Button B: X+50, Y+80\n" +
            "Prize: X=10578, Y=9983\n" +
            "\n" +
            "Button A: X+83, Y+29\n" +
            "Button B: X+20, Y+49\n" +
            "Prize: X=8704, Y=5982\n" +
            "\n" +
            "Button A: X+17, Y+83\n" +
            "Button B: X+75, Y+13\n" +
            "Prize: X=10144, Y=13144\n" +
            "\n" +
            "Button A: X+43, Y+12\n" +
            "Button B: X+33, Y+71\n" +
            "Prize: X=6221, Y=8450\n" +
            "\n" +
            "Button A: X+61, Y+27\n" +
            "Button B: X+23, Y+44\n" +
            "Prize: X=2473, Y=3834\n" +
            "\n" +
            "Button A: X+19, Y+77\n" +
            "Button B: X+68, Y+14\n" +
            "Prize: X=8635, Y=14655\n" +
            "\n" +
            "Button A: X+35, Y+97\n" +
            "Button B: X+80, Y+47\n" +
            "Prize: X=6060, Y=9981\n" +
            "\n" +
            "Button A: X+92, Y+28\n" +
            "Button B: X+48, Y+86\n" +
            "Prize: X=12368, Y=10118\n" +
            "\n" +
            "Button A: X+17, Y+63\n" +
            "Button B: X+94, Y+41\n" +
            "Prize: X=3401, Y=6764\n" +
            "\n" +
            "Button A: X+13, Y+28\n" +
            "Button B: X+57, Y+21\n" +
            "Prize: X=10116, Y=2580\n" +
            "\n" +
            "Button A: X+54, Y+16\n" +
            "Button B: X+19, Y+65\n" +
            "Prize: X=17693, Y=18057\n" +
            "\n" +
            "Button A: X+36, Y+18\n" +
            "Button B: X+18, Y+56\n" +
            "Prize: X=818, Y=10166\n" +
            "\n" +
            "Button A: X+30, Y+11\n" +
            "Button B: X+48, Y+68\n" +
            "Prize: X=680, Y=18576\n" +
            "\n" +
            "Button A: X+53, Y+63\n" +
            "Button B: X+58, Y+14\n" +
            "Prize: X=6464, Y=2464\n" +
            "\n" +
            "Button A: X+43, Y+19\n" +
            "Button B: X+33, Y+59\n" +
            "Prize: X=6935, Y=3385\n" +
            "\n" +
            "Button A: X+78, Y+42\n" +
            "Button B: X+18, Y+50\n" +
            "Prize: X=6188, Y=19540\n" +
            "\n" +
            "Button A: X+17, Y+65\n" +
            "Button B: X+91, Y+27\n" +
            "Prize: X=2552, Y=2376\n" +
            "\n" +
            "Button A: X+15, Y+15\n" +
            "Button B: X+53, Y+12\n" +
            "Prize: X=4378, Y=2082\n" +
            "\n" +
            "Button A: X+95, Y+57\n" +
            "Button B: X+31, Y+71\n" +
            "Prize: X=3543, Y=4903\n" +
            "\n" +
            "Button A: X+11, Y+39\n" +
            "Button B: X+36, Y+11\n" +
            "Prize: X=16328, Y=10583\n" +
            "\n" +
            "Button A: X+20, Y+96\n" +
            "Button B: X+33, Y+34\n" +
            "Prize: X=658, Y=2412\n" +
            "\n" +
            "Button A: X+16, Y+36\n" +
            "Button B: X+57, Y+34\n" +
            "Prize: X=2414, Y=10800\n" +
            "\n" +
            "Button A: X+17, Y+96\n" +
            "Button B: X+58, Y+19\n" +
            "Prize: X=2396, Y=4583\n" +
            "\n" +
            "Button A: X+54, Y+19\n" +
            "Button B: X+15, Y+33\n" +
            "Prize: X=830, Y=13495\n" +
            "\n" +
            "Button A: X+67, Y+21\n" +
            "Button B: X+11, Y+36\n" +
            "Prize: X=15009, Y=7019\n" +
            "\n" +
            "Button A: X+60, Y+13\n" +
            "Button B: X+97, Y+94\n" +
            "Prize: X=5471, Y=2864\n" +
            "\n" +
            "Button A: X+30, Y+53\n" +
            "Button B: X+32, Y+16\n" +
            "Prize: X=7992, Y=5652\n" +
            "\n" +
            "Button A: X+16, Y+48\n" +
            "Button B: X+58, Y+11\n" +
            "Prize: X=3442, Y=9359\n" +
            "\n" +
            "Button A: X+31, Y+65\n" +
            "Button B: X+92, Y+17\n" +
            "Prize: X=5690, Y=4015\n" +
            "\n" +
            "Button A: X+15, Y+42\n" +
            "Button B: X+61, Y+41\n" +
            "Prize: X=5312, Y=11057\n" +
            "\n" +
            "Button A: X+30, Y+12\n" +
            "Button B: X+40, Y+69\n" +
            "Prize: X=13180, Y=10529\n" +
            "\n" +
            "Button A: X+36, Y+88\n" +
            "Button B: X+52, Y+15\n" +
            "Prize: X=3644, Y=2293\n" +
            "\n" +
            "Button A: X+22, Y+41\n" +
            "Button B: X+48, Y+27\n" +
            "Prize: X=14570, Y=13331\n" +
            "\n" +
            "Button A: X+17, Y+75\n" +
            "Button B: X+25, Y+26\n" +
            "Prize: X=2776, Y=7358\n" +
            "\n" +
            "Button A: X+12, Y+69\n" +
            "Button B: X+55, Y+14\n" +
            "Prize: X=14183, Y=12248\n" +
            "\n" +
            "Button A: X+25, Y+14\n" +
            "Button B: X+23, Y+51\n" +
            "Prize: X=14705, Y=18449\n" +
            "\n" +
            "Button A: X+47, Y+35\n" +
            "Button B: X+32, Y+79\n" +
            "Prize: X=3796, Y=3213\n" +
            "\n" +
            "Button A: X+49, Y+22\n" +
            "Button B: X+26, Y+63\n" +
            "Prize: X=14674, Y=2732\n" +
            "\n" +
            "Button A: X+17, Y+81\n" +
            "Button B: X+70, Y+12\n" +
            "Prize: X=11287, Y=5867\n" +
            "\n" +
            "Button A: X+25, Y+65\n" +
            "Button B: X+75, Y+57\n" +
            "Prize: X=3550, Y=3848\n" +
            "\n" +
            "Button A: X+63, Y+16\n" +
            "Button B: X+59, Y+85\n" +
            "Prize: X=5698, Y=5368\n" +
            "\n" +
            "Button A: X+39, Y+67\n" +
            "Button B: X+71, Y+19\n" +
            "Prize: X=2629, Y=2457\n" +
            "\n" +
            "Button A: X+49, Y+24\n" +
            "Button B: X+12, Y+51\n" +
            "Prize: X=8580, Y=13931\n" +
            "\n" +
            "Button A: X+30, Y+63\n" +
            "Button B: X+70, Y+15\n" +
            "Prize: X=3420, Y=3618\n" +
            "\n" +
            "Button A: X+21, Y+58\n" +
            "Button B: X+63, Y+33\n" +
            "Prize: X=14774, Y=8692\n" +
            "\n" +
            "Button A: X+63, Y+32\n" +
            "Button B: X+15, Y+76\n" +
            "Prize: X=3012, Y=2624\n" +
            "\n" +
            "Button A: X+39, Y+17\n" +
            "Button B: X+43, Y+89\n" +
            "Prize: X=3593, Y=2339\n" +
            "\n" +
            "Button A: X+17, Y+37\n" +
            "Button B: X+75, Y+47\n" +
            "Prize: X=13809, Y=19685\n" +
            "\n" +
            "Button A: X+26, Y+59\n" +
            "Button B: X+63, Y+20\n" +
            "Prize: X=5513, Y=3780\n" +
            "\n" +
            "Button A: X+16, Y+80\n" +
            "Button B: X+21, Y+18\n" +
            "Prize: X=1089, Y=1530\n" +
            "\n" +
            "Button A: X+15, Y+50\n" +
            "Button B: X+53, Y+26\n" +
            "Prize: X=1032, Y=7684\n" +
            "\n" +
            "Button A: X+25, Y+46\n" +
            "Button B: X+54, Y+22\n" +
            "Prize: X=13051, Y=15284\n" +
            "\n" +
            "Button A: X+58, Y+49\n" +
            "Button B: X+20, Y+81\n" +
            "Prize: X=1126, Y=3259\n" +
            "\n" +
            "Button A: X+16, Y+43\n" +
            "Button B: X+32, Y+17\n" +
            "Prize: X=9872, Y=14960\n" +
            "\n" +
            "Button A: X+72, Y+18\n" +
            "Button B: X+49, Y+95\n" +
            "Prize: X=4374, Y=5562\n" +
            "\n" +
            "Button A: X+96, Y+24\n" +
            "Button B: X+40, Y+70\n" +
            "Prize: X=8040, Y=5430\n" +
            "\n" +
            "Button A: X+25, Y+51\n" +
            "Button B: X+56, Y+15\n" +
            "Prize: X=4219, Y=1263\n" +
            "\n" +
            "Button A: X+40, Y+98\n" +
            "Button B: X+88, Y+43\n" +
            "Prize: X=4064, Y=3398\n" +
            "\n" +
            "Button A: X+16, Y+22\n" +
            "Button B: X+82, Y+27\n" +
            "Prize: X=6482, Y=2653\n" +
            "\n" +
            "Button A: X+88, Y+14\n" +
            "Button B: X+40, Y+60\n" +
            "Prize: X=5880, Y=2330\n" +
            "\n" +
            "Button A: X+30, Y+74\n" +
            "Button B: X+55, Y+20\n" +
            "Prize: X=2405, Y=16892\n" +
            "\n" +
            "Button A: X+18, Y+47\n" +
            "Button B: X+62, Y+12\n" +
            "Prize: X=19226, Y=2795\n" +
            "\n" +
            "Button A: X+50, Y+18\n" +
            "Button B: X+38, Y+58\n" +
            "Prize: X=4712, Y=6084\n" +
            "\n" +
            "Button A: X+14, Y+39\n" +
            "Button B: X+63, Y+35\n" +
            "Prize: X=14746, Y=8492\n" +
            "\n" +
            "Button A: X+59, Y+13\n" +
            "Button B: X+35, Y+85\n" +
            "Prize: X=595, Y=9525\n" +
            "\n" +
            "Button A: X+42, Y+22\n" +
            "Button B: X+36, Y+62\n" +
            "Prize: X=2822, Y=9926\n" +
            "\n" +
            "Button A: X+45, Y+55\n" +
            "Button B: X+94, Y+22\n" +
            "Prize: X=6660, Y=3960\n" +
            "\n" +
            "Button A: X+41, Y+90\n" +
            "Button B: X+50, Y+32\n" +
            "Prize: X=5513, Y=6270\n" +
            "\n" +
            "Button A: X+84, Y+74\n" +
            "Button B: X+12, Y+37\n" +
            "Prize: X=4500, Y=5735\n" +
            "\n" +
            "Button A: X+31, Y+49\n" +
            "Button B: X+48, Y+13\n" +
            "Prize: X=3595, Y=4425\n" +
            "\n" +
            "Button A: X+99, Y+18\n" +
            "Button B: X+31, Y+73\n" +
            "Prize: X=4116, Y=2163\n" +
            "\n" +
            "Button A: X+56, Y+31\n" +
            "Button B: X+29, Y+53\n" +
            "Prize: X=4032, Y=16493\n" +
            "\n" +
            "Button A: X+45, Y+77\n" +
            "Button B: X+50, Y+18\n" +
            "Prize: X=18500, Y=2116\n" +
            "\n" +
            "Button A: X+81, Y+17\n" +
            "Button B: X+48, Y+65\n" +
            "Prize: X=12237, Y=7951\n" +
            "\n" +
            "Button A: X+48, Y+57\n" +
            "Button B: X+98, Y+16\n" +
            "Prize: X=10492, Y=6236\n" +
            "\n" +
            "Button A: X+70, Y+12\n" +
            "Button B: X+14, Y+78\n" +
            "Prize: X=17210, Y=9854\n" +
            "\n" +
            "Button A: X+53, Y+86\n" +
            "Button B: X+45, Y+13\n" +
            "Prize: X=18553, Y=8777\n" +
            "\n" +
            "Button A: X+15, Y+34\n" +
            "Button B: X+72, Y+46\n" +
            "Prize: X=17951, Y=17142\n" +
            "\n" +
            "Button A: X+26, Y+43\n" +
            "Button B: X+77, Y+26\n" +
            "Prize: X=4778, Y=4659\n" +
            "\n" +
            "Button A: X+12, Y+66\n" +
            "Button B: X+59, Y+44\n" +
            "Prize: X=3031, Y=5170\n" +
            "\n" +
            "Button A: X+26, Y+14\n" +
            "Button B: X+20, Y+58\n" +
            "Prize: X=1516, Y=4028\n" +
            "\n" +
            "Button A: X+60, Y+13\n" +
            "Button B: X+13, Y+51\n" +
            "Prize: X=15963, Y=14692\n" +
            "\n" +
            "Button A: X+67, Y+16\n" +
            "Button B: X+11, Y+25\n" +
            "Prize: X=680, Y=319\n" +
            "\n" +
            "Button A: X+56, Y+20\n" +
            "Button B: X+15, Y+66\n" +
            "Prize: X=7292, Y=19232\n" +
            "\n" +
            "Button A: X+13, Y+96\n" +
            "Button B: X+80, Y+48\n" +
            "Prize: X=1498, Y=6720\n" +
            "\n" +
            "Button A: X+35, Y+44\n" +
            "Button B: X+11, Y+91\n" +
            "Prize: X=2406, Y=5417\n" +
            "\n" +
            "Button A: X+59, Y+23\n" +
            "Button B: X+23, Y+47\n" +
            "Prize: X=3805, Y=10093\n" +
            "\n" +
            "Button A: X+17, Y+65\n" +
            "Button B: X+99, Y+97\n" +
            "Prize: X=5914, Y=9662\n" +
            "\n" +
            "Button A: X+41, Y+83\n" +
            "Button B: X+47, Y+12\n" +
            "Prize: X=3330, Y=1503\n" +
            "\n" +
            "Button A: X+46, Y+11\n" +
            "Button B: X+22, Y+51\n" +
            "Prize: X=12508, Y=3726\n" +
            "\n" +
            "Button A: X+62, Y+21\n" +
            "Button B: X+18, Y+43\n" +
            "Prize: X=6706, Y=5563\n" +
            "\n" +
            "Button A: X+41, Y+55\n" +
            "Button B: X+51, Y+20\n" +
            "Prize: X=3715, Y=1885\n" +
            "\n" +
            "Button A: X+14, Y+41\n" +
            "Button B: X+64, Y+29\n" +
            "Prize: X=18418, Y=19077\n" +
            "\n" +
            "Button A: X+37, Y+16\n" +
            "Button B: X+33, Y+47\n" +
            "Prize: X=17950, Y=4251\n" +
            "\n" +
            "Button A: X+24, Y+68\n" +
            "Button B: X+91, Y+66\n" +
            "Prize: X=6172, Y=7512\n" +
            "\n" +
            "Button A: X+53, Y+96\n" +
            "Button B: X+62, Y+15\n" +
            "Prize: X=10393, Y=10749\n" +
            "\n" +
            "Button A: X+44, Y+27\n" +
            "Button B: X+20, Y+97\n" +
            "Prize: X=2676, Y=7573\n" +
            "\n" +
            "Button A: X+14, Y+37\n" +
            "Button B: X+97, Y+44\n" +
            "Prize: X=4964, Y=2926\n" +
            "\n" +
            "Button A: X+20, Y+55\n" +
            "Button B: X+40, Y+17\n" +
            "Prize: X=13460, Y=13556\n" +
            "\n" +
            "Button A: X+63, Y+33\n" +
            "Button B: X+34, Y+63\n" +
            "Prize: X=6045, Y=644\n" +
            "\n" +
            "Button A: X+48, Y+82\n" +
            "Button B: X+60, Y+33\n" +
            "Prize: X=4152, Y=3896\n" +
            "\n" +
            "Button A: X+18, Y+87\n" +
            "Button B: X+95, Y+24\n" +
            "Prize: X=3820, Y=7149\n" +
            "\n" +
            "Button A: X+90, Y+47\n" +
            "Button B: X+18, Y+41\n" +
            "Prize: X=5688, Y=5530\n" +
            "\n" +
            "Button A: X+94, Y+34\n" +
            "Button B: X+18, Y+72\n" +
            "Prize: X=5798, Y=6092\n" +
            "\n" +
            "Button A: X+37, Y+11\n" +
            "Button B: X+36, Y+68\n" +
            "Prize: X=1009, Y=9527\n" +
            "\n" +
            "Button A: X+82, Y+53\n" +
            "Button B: X+13, Y+36\n" +
            "Prize: X=6514, Y=8211\n" +
            "\n" +
            "Button A: X+37, Y+12\n" +
            "Button B: X+24, Y+49\n" +
            "Prize: X=19416, Y=15591\n" +
            "\n" +
            "Button A: X+70, Y+60\n" +
            "Button B: X+17, Y+50\n" +
            "Prize: X=7214, Y=8380\n" +
            "\n" +
            "Button A: X+67, Y+11\n" +
            "Button B: X+12, Y+16\n" +
            "Prize: X=5442, Y=1146\n" +
            "\n" +
            "Button A: X+47, Y+99\n" +
            "Button B: X+77, Y+46\n" +
            "Prize: X=1351, Y=1800\n" +
            "\n" +
            "Button A: X+74, Y+33\n" +
            "Button B: X+46, Y+90\n" +
            "Prize: X=5668, Y=5724\n" +
            "\n" +
            "Button A: X+51, Y+81\n" +
            "Button B: X+61, Y+25\n" +
            "Prize: X=5878, Y=6676\n" +
            "\n" +
            "Button A: X+29, Y+93\n" +
            "Button B: X+97, Y+25\n" +
            "Prize: X=10685, Y=6517\n" +
            "\n" +
            "Button A: X+83, Y+89\n" +
            "Button B: X+67, Y+17\n" +
            "Prize: X=4108, Y=3692\n" +
            "\n" +
            "Button A: X+18, Y+46\n" +
            "Button B: X+66, Y+18\n" +
            "Prize: X=17690, Y=9366\n" +
            "\n" +
            "Button A: X+75, Y+90\n" +
            "Button B: X+12, Y+91\n" +
            "Prize: X=7860, Y=15560\n" +
            "\n" +
            "Button A: X+21, Y+72\n" +
            "Button B: X+72, Y+17\n" +
            "Prize: X=18143, Y=1514\n" +
            "\n" +
            "Button A: X+33, Y+15\n" +
            "Button B: X+34, Y+50\n" +
            "Prize: X=11081, Y=12875\n" +
            "\n" +
            "Button A: X+40, Y+19\n" +
            "Button B: X+15, Y+39\n" +
            "Prize: X=7060, Y=11866\n" +
            "\n" +
            "Button A: X+56, Y+41\n" +
            "Button B: X+17, Y+39\n" +
            "Prize: X=12452, Y=429\n" +
            "\n" +
            "Button A: X+67, Y+81\n" +
            "Button B: X+13, Y+79\n" +
            "Prize: X=3428, Y=8764\n" +
            "\n" +
            "Button A: X+20, Y+51\n" +
            "Button B: X+67, Y+23\n" +
            "Prize: X=13378, Y=19419\n" +
            "\n" +
            "Button A: X+61, Y+42\n" +
            "Button B: X+12, Y+38\n" +
            "Prize: X=6443, Y=2700\n" +
            "\n" +
            "Button A: X+13, Y+41\n" +
            "Button B: X+66, Y+29\n" +
            "Prize: X=16250, Y=275\n" +
            "\n" +
            "Button A: X+47, Y+12\n" +
            "Button B: X+25, Y+70\n" +
            "Prize: X=3405, Y=3650\n" +
            "\n" +
            "Button A: X+95, Y+60\n" +
            "Button B: X+23, Y+79\n" +
            "Prize: X=4259, Y=6107\n" +
            "\n" +
            "Button A: X+52, Y+18\n" +
            "Button B: X+19, Y+59\n" +
            "Prize: X=3656, Y=3428\n" +
            "\n" +
            "Button A: X+33, Y+24\n" +
            "Button B: X+30, Y+78\n" +
            "Prize: X=3399, Y=7416\n" +
            "\n" +
            "Button A: X+27, Y+19\n" +
            "Button B: X+14, Y+34\n" +
            "Prize: X=5847, Y=1191\n" +
            "\n" +
            "Button A: X+93, Y+17\n" +
            "Button B: X+66, Y+64\n" +
            "Prize: X=10617, Y=3343\n" +
            "\n" +
            "Button A: X+17, Y+62\n" +
            "Button B: X+27, Y+12\n" +
            "Prize: X=4316, Y=8096\n" +
            "\n" +
            "Button A: X+13, Y+50\n" +
            "Button B: X+62, Y+29\n" +
            "Prize: X=19495, Y=19244\n" +
            "\n" +
            "Button A: X+26, Y+56\n" +
            "Button B: X+93, Y+60\n" +
            "Prize: X=647, Y=692\n" +
            "\n" +
            "Button A: X+91, Y+50\n" +
            "Button B: X+34, Y+90\n" +
            "Prize: X=9264, Y=8870\n" +
            "\n" +
            "Button A: X+15, Y+34\n" +
            "Button B: X+97, Y+59\n" +
            "Prize: X=4151, Y=3296\n" +
            "\n" +
            "Button A: X+81, Y+71\n" +
            "Button B: X+13, Y+78\n" +
            "Prize: X=1450, Y=4135\n" +
            "\n" +
            "Button A: X+16, Y+45\n" +
            "Button B: X+60, Y+39\n" +
            "Prize: X=5084, Y=4856\n" +
            "\n" +
            "Button A: X+48, Y+82\n" +
            "Button B: X+90, Y+37\n" +
            "Prize: X=11112, Y=10110\n" +
            "\n" +
            "Button A: X+28, Y+72\n" +
            "Button B: X+87, Y+40\n" +
            "Prize: X=6010, Y=4064\n" +
            "\n" +
            "Button A: X+94, Y+12\n" +
            "Button B: X+30, Y+88\n" +
            "Prize: X=1876, Y=4448\n" +
            "\n" +
            "Button A: X+11, Y+86\n" +
            "Button B: X+97, Y+84\n" +
            "Prize: X=1455, Y=8678\n" +
            "\n" +
            "Button A: X+14, Y+51\n" +
            "Button B: X+69, Y+29\n" +
            "Prize: X=14379, Y=14593\n" +
            "\n" +
            "Button A: X+21, Y+79\n" +
            "Button B: X+94, Y+71\n" +
            "Prize: X=1611, Y=2669\n" +
            "\n" +
            "Button A: X+75, Y+46\n" +
            "Button B: X+12, Y+26\n" +
            "Prize: X=5360, Y=5142\n" +
            "\n" +
            "Button A: X+76, Y+43\n" +
            "Button B: X+30, Y+76\n" +
            "Prize: X=8082, Y=9708\n" +
            "\n" +
            "Button A: X+32, Y+99\n" +
            "Button B: X+28, Y+14\n" +
            "Prize: X=1068, Y=2360\n" +
            "\n" +
            "Button A: X+23, Y+63\n" +
            "Button B: X+49, Y+22\n" +
            "Prize: X=19299, Y=13706\n" +
            "\n" +
            "Button A: X+87, Y+24\n" +
            "Button B: X+46, Y+98\n" +
            "Prize: X=11245, Y=8306\n" +
            "\n" +
            "Button A: X+96, Y+50\n" +
            "Button B: X+23, Y+61\n" +
            "Prize: X=9190, Y=8414\n" +
            "\n" +
            "Button A: X+22, Y+77\n" +
            "Button B: X+74, Y+12\n" +
            "Prize: X=13870, Y=16135\n" +
            "\n" +
            "Button A: X+87, Y+52\n" +
            "Button B: X+33, Y+98\n" +
            "Prize: X=5268, Y=6358\n" +
            "\n" +
            "Button A: X+28, Y+79\n" +
            "Button B: X+48, Y+37\n" +
            "Prize: X=2100, Y=4547\n" +
            "\n" +
            "Button A: X+44, Y+83\n" +
            "Button B: X+57, Y+15\n" +
            "Prize: X=9033, Y=8805\n" +
            "\n" +
            "Button A: X+32, Y+76\n" +
            "Button B: X+42, Y+21\n" +
            "Prize: X=4042, Y=4481\n" +
            "\n" +
            "Button A: X+73, Y+11\n" +
            "Button B: X+78, Y+68\n" +
            "Prize: X=12004, Y=7096\n" +
            "\n" +
            "Button A: X+59, Y+19\n" +
            "Button B: X+12, Y+56\n" +
            "Prize: X=15540, Y=13528\n" +
            "\n" +
            "Button A: X+46, Y+28\n" +
            "Button B: X+12, Y+44\n" +
            "Prize: X=19412, Y=3044\n" +
            "\n" +
            "Button A: X+14, Y+24\n" +
            "Button B: X+46, Y+21\n" +
            "Prize: X=16360, Y=995\n" +
            "\n" +
            "Button A: X+29, Y+50\n" +
            "Button B: X+23, Y+13\n" +
            "Prize: X=15789, Y=7136\n" +
            "\n" +
            "Button A: X+12, Y+48\n" +
            "Button B: X+81, Y+40\n" +
            "Prize: X=9497, Y=8424\n" +
            "\n" +
            "Button A: X+70, Y+16\n" +
            "Button B: X+11, Y+46\n" +
            "Prize: X=9728, Y=16688\n" +
            "\n" +
            "Button A: X+64, Y+39\n" +
            "Button B: X+18, Y+42\n" +
            "Prize: X=5064, Y=3210\n" +
            "\n" +
            "Button A: X+12, Y+42\n" +
            "Button B: X+95, Y+67\n" +
            "Prize: X=6954, Y=6816\n" +
            "\n" +
            "Button A: X+71, Y+26\n" +
            "Button B: X+58, Y+78\n" +
            "Prize: X=6368, Y=3978\n" +
            "\n" +
            "Button A: X+34, Y+21\n" +
            "Button B: X+25, Y+48\n" +
            "Prize: X=8873, Y=2960\n" +
            "\n" +
            "Button A: X+13, Y+44\n" +
            "Button B: X+82, Y+46\n" +
            "Prize: X=8218, Y=14754\n" +
            "\n" +
            "Button A: X+23, Y+52\n" +
            "Button B: X+43, Y+13\n" +
            "Prize: X=4439, Y=7621\n" +
            "\n" +
            "Button A: X+57, Y+22\n" +
            "Button B: X+30, Y+63\n" +
            "Prize: X=18083, Y=10440\n";

    public static Input getSample() {
        return new Input(sampleData);
    }
    public static Input getData() {
        return new Input(trueData);
    }
}
