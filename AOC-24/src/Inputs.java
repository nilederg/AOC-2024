import Library.Quadruplet;
import Management.Input;

public class Inputs {
    public static final int phase = 2;

    // Sample data
    // A: Sample input
    // B: Phase 1 expected output
    // C: Phase 2 expected output
    // D: To print or not to print
    public static final Quadruplet<String, Object, Object, Boolean>[] samples = new Quadruplet[]{
            new Quadruplet<>(
                    "x00: 1\n" +
                            "x01: 0\n" +
                            "x02: 1\n" +
                            "x03: 1\n" +
                            "x04: 0\n" +
                            "y00: 1\n" +
                            "y01: 1\n" +
                            "y02: 1\n" +
                            "y03: 1\n" +
                            "y04: 1\n" +
                            "\n" +
                            "ntg XOR fgs -> mjb\n" +
                            "y02 OR x01 -> tnw\n" +
                            "kwq OR kpj -> z05\n" +
                            "x00 OR x03 -> fst\n" +
                            "tgd XOR rvg -> z01\n" +
                            "vdt OR tnw -> bfw\n" +
                            "bfw AND frj -> z10\n" +
                            "ffh OR nrd -> bqk\n" +
                            "y00 AND y03 -> djm\n" +
                            "y03 OR y00 -> psh\n" +
                            "bqk OR frj -> z08\n" +
                            "tnw OR fst -> frj\n" +
                            "gnj AND tgd -> z11\n" +
                            "bfw XOR mjb -> z00\n" +
                            "x03 OR x00 -> vdt\n" +
                            "gnj AND wpb -> z02\n" +
                            "x04 AND y00 -> kjc\n" +
                            "djm OR pbm -> qhw\n" +
                            "nrd AND vdt -> hwm\n" +
                            "kjc AND fst -> rvg\n" +
                            "y04 OR y02 -> fgs\n" +
                            "y01 AND x02 -> pbm\n" +
                            "ntg OR kjc -> kwq\n" +
                            "psh XOR fgs -> tgd\n" +
                            "qhw XOR tgd -> z09\n" +
                            "pbm OR djm -> kpj\n" +
                            "x03 XOR y03 -> ffh\n" +
                            "x00 XOR y04 -> ntg\n" +
                            "bfw OR bqk -> z06\n" +
                            "nrd XOR fgs -> wpb\n" +
                            "frj XOR qhw -> z04\n" +
                            "bqk OR frj -> z07\n" +
                            "y03 OR x01 -> nrd\n" +
                            "hwm AND bqk -> z03\n" +
                            "tgd XOR rvg -> z12\n" +
                            "tnw OR pbm -> gnj"
                    , (Object) 2024L
                    , (Object) null
                    , (Boolean) true
            ),
            new Quadruplet<>(
                    "x00: 0\n" +
                            "x01: 1\n" +
                            "x02: 0\n" +
                            "x03: 1\n" +
                            "x04: 0\n" +
                            "x05: 1\n" +
                            "y00: 0\n" +
                            "y01: 0\n" +
                            "y02: 1\n" +
                            "y03: 1\n" +
                            "y04: 0\n" +
                            "y05: 1\n" +
                            "\n" +
                            "x00 AND y00 -> z05\n" +
                            "x01 AND y01 -> z02\n" +
                            "x02 AND y02 -> z01\n" +
                            "x03 AND y03 -> z03\n" +
                            "x04 AND y04 -> z04\n" +
                            "x05 AND y05 -> z00"
                    , (Object) null
                    , (Object) null
                    , (Boolean) true
            ),
            new Quadruplet<>(
                    ""
                    , (Object) null
                    , (Object) null
                    , (Boolean) false
            ),
    };
    // Puzzle data
    public static final long[] tooLow1  = new long[] {};
    public static final long[] tooHigh1 = new long[] {};
    public static final long[] tooLow2  = new long[] {};
    public static final long[] tooHigh2 = new long[] {};
    public static final boolean printTrueData = true;
    public static final String trueData = "x00: 1\n" +
            "x01: 0\n" +
            "x02: 0\n" +
            "x03: 1\n" +
            "x04: 1\n" +
            "x05: 1\n" +
            "x06: 0\n" +
            "x07: 0\n" +
            "x08: 0\n" +
            "x09: 1\n" +
            "x10: 0\n" +
            "x11: 0\n" +
            "x12: 0\n" +
            "x13: 0\n" +
            "x14: 0\n" +
            "x15: 1\n" +
            "x16: 1\n" +
            "x17: 0\n" +
            "x18: 1\n" +
            "x19: 0\n" +
            "x20: 0\n" +
            "x21: 0\n" +
            "x22: 0\n" +
            "x23: 1\n" +
            "x24: 1\n" +
            "x25: 0\n" +
            "x26: 0\n" +
            "x27: 0\n" +
            "x28: 1\n" +
            "x29: 0\n" +
            "x30: 1\n" +
            "x31: 0\n" +
            "x32: 0\n" +
            "x33: 0\n" +
            "x34: 1\n" +
            "x35: 1\n" +
            "x36: 1\n" +
            "x37: 0\n" +
            "x38: 1\n" +
            "x39: 1\n" +
            "x40: 0\n" +
            "x41: 0\n" +
            "x42: 0\n" +
            "x43: 1\n" +
            "x44: 1\n" +
            "y00: 1\n" +
            "y01: 1\n" +
            "y02: 1\n" +
            "y03: 1\n" +
            "y04: 0\n" +
            "y05: 1\n" +
            "y06: 0\n" +
            "y07: 1\n" +
            "y08: 0\n" +
            "y09: 1\n" +
            "y10: 1\n" +
            "y11: 1\n" +
            "y12: 1\n" +
            "y13: 1\n" +
            "y14: 1\n" +
            "y15: 0\n" +
            "y16: 0\n" +
            "y17: 0\n" +
            "y18: 1\n" +
            "y19: 1\n" +
            "y20: 1\n" +
            "y21: 1\n" +
            "y22: 1\n" +
            "y23: 1\n" +
            "y24: 0\n" +
            "y25: 1\n" +
            "y26: 1\n" +
            "y27: 0\n" +
            "y28: 1\n" +
            "y29: 0\n" +
            "y30: 1\n" +
            "y31: 0\n" +
            "y32: 1\n" +
            "y33: 0\n" +
            "y34: 1\n" +
            "y35: 1\n" +
            "y36: 0\n" +
            "y37: 1\n" +
            "y38: 0\n" +
            "y39: 0\n" +
            "y40: 0\n" +
            "y41: 0\n" +
            "y42: 0\n" +
            "y43: 0\n" +
            "y44: 1\n" +
            "\n" +
            "rds AND wpc -> cmj\n" +
            "vbn XOR mkk -> z13\n" +
            "y04 AND x04 -> bbw\n" +
            "ftn OR swv -> bmw\n" +
            "dkj AND gqs -> vbm\n" +
            "x38 XOR y38 -> bhv\n" +
            "jhh XOR vws -> z04\n" +
            "y38 AND x38 -> tbv\n" +
            "whm AND rbn -> tpt\n" +
            "fkf XOR whs -> z31\n" +
            "x37 XOR y37 -> bmt\n" +
            "y43 AND x43 -> jbc\n" +
            "qkj XOR fdg -> z15\n" +
            "tsq XOR pmr -> z21\n" +
            "x11 XOR y11 -> jtg\n" +
            "x35 XOR y35 -> hqk\n" +
            "whs AND fkf -> vpr\n" +
            "y16 XOR x16 -> sbt\n" +
            "y15 AND x15 -> gmt\n" +
            "tdp AND qqs -> sph\n" +
            "bsn OR bdc -> mkk\n" +
            "y12 AND x12 -> bdc\n" +
            "qmj OR rmv -> tdp\n" +
            "fdg AND qkj -> fjw\n" +
            "wfd XOR cwj -> z36\n" +
            "rgm OR vgp -> cvt\n" +
            "vws AND jhh -> shg\n" +
            "qgd OR ggn -> cvr\n" +
            "x28 AND y28 -> rtf\n" +
            "y42 XOR x42 -> jgh\n" +
            "jsv OR kbr -> cwj\n" +
            "hdp OR rtr -> gms\n" +
            "gdm OR kqg -> wpc\n" +
            "x00 XOR y00 -> z00\n" +
            "x17 AND y17 -> qqc\n" +
            "hrt AND sbt -> dfq\n" +
            "y07 AND x07 -> nsg\n" +
            "y18 AND x18 -> z18\n" +
            "mjm AND njj -> bch\n" +
            "y39 AND x39 -> qnt\n" +
            "x16 AND y16 -> hsb\n" +
            "grq XOR vfq -> z28\n" +
            "rbn XOR whm -> z40\n" +
            "x25 AND y25 -> rwp\n" +
            "x41 AND y41 -> hdp\n" +
            "x42 AND y42 -> ftn\n" +
            "rcm AND rhd -> qvh\n" +
            "wsq XOR cvr -> z19\n" +
            "rwp OR bvm -> gdq\n" +
            "kgd OR kqf -> z10\n" +
            "x40 AND y40 -> gkj\n" +
            "x09 XOR y09 -> rds\n" +
            "jhm AND fqt -> rtr\n" +
            "y07 XOR x07 -> jfr\n" +
            "x35 AND y35 -> kbr\n" +
            "sqr XOR mwq -> mwk\n" +
            "hmc AND brk -> pjk\n" +
            "y23 XOR x23 -> gwq\n" +
            "tdd OR nsg -> cct\n" +
            "y14 XOR x14 -> njj\n" +
            "wtm XOR phh -> z06\n" +
            "wws AND cdh -> dff\n" +
            "thq XOR mfd -> z17\n" +
            "y13 XOR x13 -> vbn\n" +
            "y24 XOR x24 -> hsw\n" +
            "x12 XOR y12 -> jnk\n" +
            "vvj OR hph -> jsp\n" +
            "swf XOR hqk -> z35\n" +
            "jjg OR pjp -> tqk\n" +
            "vbm OR wss -> whs\n" +
            "x22 XOR y22 -> kdn\n" +
            "y11 AND x11 -> pjp\n" +
            "x08 XOR y08 -> cjn\n" +
            "std XOR jfr -> z07\n" +
            "tjj OR vpp -> jhh\n" +
            "pjk OR vpj -> rhd\n" +
            "vkd XOR vtd -> z34\n" +
            "cjn AND cct -> kqg\n" +
            "hnw OR nth -> phh\n" +
            "x30 AND y30 -> wss\n" +
            "ptr OR dnq -> wrn\n" +
            "wfd AND cwj -> vvj\n" +
            "wpd AND ndm -> jcq\n" +
            "jmh XOR mrs -> z24\n" +
            "x29 XOR y29 -> wws\n" +
            "x33 AND y33 -> wvp\n" +
            "y02 AND x02 -> wwc\n" +
            "nfh XOR nqq -> qgd\n" +
            "y30 XOR x30 -> gqs\n" +
            "jcq OR qnt -> rbn\n" +
            "wws XOR cdh -> z29\n" +
            "bmn OR cmj -> mwq\n" +
            "y06 AND x06 -> mgc\n" +
            "wkt OR ddd -> swf\n" +
            "phh AND wtm -> cjd\n" +
            "wvp OR gqp -> vtd\n" +
            "jhm XOR fqt -> z41\n" +
            "x20 XOR y20 -> vcn\n" +
            "gms AND jgh -> swv\n" +
            "y23 AND x23 -> sdd\n" +
            "ndb OR qqc -> nfh\n" +
            "png XOR gdq -> z26\n" +
            "nfh AND nqq -> ggn\n" +
            "x40 XOR y40 -> whm\n" +
            "y33 XOR x33 -> wwp\n" +
            "sph OR hpb -> vfq\n" +
            "vjs OR sfm -> cpv\n" +
            "y43 XOR x43 -> bng\n" +
            "cct XOR cjn -> z08\n" +
            "jmh AND mrs -> bmv\n" +
            "x01 XOR y01 -> brk\n" +
            "tsq AND pmr -> vjs\n" +
            "cwt AND mcd -> pcq\n" +
            "jfr AND std -> tdd\n" +
            "sbt XOR hrt -> z16\n" +
            "y20 AND x20 -> twp\n" +
            "y05 AND x05 -> hnw\n" +
            "y27 XOR x27 -> qqs\n" +
            "bmv OR hsw -> mtn\n" +
            "y10 XOR x10 -> sqr\n" +
            "bmt XOR jsp -> z37\n" +
            "sdd OR hcp -> mrs\n" +
            "shg OR bbw -> ckj\n" +
            "y04 XOR x04 -> vws\n" +
            "jsp AND bmt -> mwp\n" +
            "png AND gdq -> rmv\n" +
            "mwk XOR jtg -> z11\n" +
            "twp OR rqv -> tsq\n" +
            "x19 AND y19 -> dtr\n" +
            "x18 XOR y18 -> nqq\n" +
            "wpc XOR rds -> z09\n" +
            "kdn XOR cpv -> z22\n" +
            "x31 AND y31 -> vjp\n" +
            "rtf OR cbh -> cdh\n" +
            "dkj XOR gqs -> z30\n" +
            "y34 XOR x34 -> vkd\n" +
            "mgc OR cjd -> std\n" +
            "tff AND mtn -> bvm\n" +
            "gwq AND wrn -> hcp\n" +
            "vcn AND spr -> rqv\n" +
            "y44 XOR x44 -> mcd\n" +
            "y01 AND x01 -> vpj\n" +
            "vbn AND mkk -> tsd\n" +
            "tqk AND jnk -> bsn\n" +
            "x27 AND y27 -> hpb\n" +
            "bhv AND kqb -> nrc\n" +
            "spr XOR vcn -> z20\n" +
            "ckj XOR tbw -> z05\n" +
            "y22 AND x22 -> ptr\n" +
            "ckj AND tbw -> nth\n" +
            "cpv AND kdn -> dnq\n" +
            "fjw OR gmt -> hrt\n" +
            "qvh OR wwc -> sgb\n" +
            "gwq XOR wrn -> z23\n" +
            "jgh XOR gms -> z42\n" +
            "y32 AND x32 -> vgp\n" +
            "mwq AND sqr -> kgd\n" +
            "gkj OR tpt -> fqt\n" +
            "x34 AND y34 -> wkt\n" +
            "x14 AND y14 -> pwr\n" +
            "y31 XOR x31 -> fkf\n" +
            "mwk AND jtg -> jjg\n" +
            "y05 XOR x05 -> tbw\n" +
            "pwr OR bch -> fdg\n" +
            "x21 AND y21 -> sfm\n" +
            "vfq AND grq -> cbh\n" +
            "x41 XOR y41 -> jhm\n" +
            "y36 XOR x36 -> wfd\n" +
            "mcr AND bgf -> rgm\n" +
            "psb XOR sgb -> z03\n" +
            "bmw AND bng -> trs\n" +
            "x02 XOR y02 -> rcm\n" +
            "jnk XOR tqk -> z12\n" +
            "dfq OR hsb -> mfd\n" +
            "vtd AND vkd -> ddd\n" +
            "bhv XOR kqb -> z38\n" +
            "y37 AND x37 -> ftj\n" +
            "fmp OR pcq -> z45\n" +
            "brk XOR hmc -> z01\n" +
            "dff OR hcb -> dkj\n" +
            "cvt AND wwp -> z33\n" +
            "x13 AND y13 -> vwv\n" +
            "qqs XOR tdp -> z27\n" +
            "x26 XOR y26 -> png\n" +
            "x15 XOR y15 -> qkj\n" +
            "x17 XOR y17 -> thq\n" +
            "trs OR jbc -> cwt\n" +
            "y36 AND x36 -> hph\n" +
            "x10 AND y10 -> kqf\n" +
            "x06 XOR y06 -> wtm\n" +
            "x32 XOR y32 -> mcr\n" +
            "mtn XOR tff -> z25\n" +
            "y28 XOR x28 -> grq\n" +
            "mwp OR ftj -> kqb\n" +
            "x03 XOR y03 -> psb\n" +
            "x25 XOR y25 -> tff\n" +
            "njj XOR mjm -> z14\n" +
            "y03 AND x03 -> vpp\n" +
            "x00 AND y00 -> hmc\n" +
            "mfd AND thq -> ndb\n" +
            "x26 AND y26 -> qmj\n" +
            "x29 AND y29 -> hcb\n" +
            "y19 XOR x19 -> wsq\n" +
            "bgf XOR mcr -> z32\n" +
            "vpr OR vjp -> bgf\n" +
            "rhd XOR rcm -> z02\n" +
            "bmw XOR bng -> z43\n" +
            "nrc OR tbv -> ndm\n" +
            "y24 AND x24 -> jmh\n" +
            "x44 AND y44 -> fmp\n" +
            "wwp XOR cvt -> gqp\n" +
            "cvr AND wsq -> ghw\n" +
            "x39 XOR y39 -> wpd\n" +
            "x09 AND y09 -> bmn\n" +
            "hqk AND swf -> jsv\n" +
            "y08 AND x08 -> gdm\n" +
            "sgb AND psb -> tjj\n" +
            "vwv OR tsd -> mjm\n" +
            "y21 XOR x21 -> pmr\n" +
            "cwt XOR mcd -> z44\n" +
            "ghw OR dtr -> spr\n" +
            "wpd XOR ndm -> z39\n";

    public static Input getData() {
        return new Input(trueData);
    }
}
