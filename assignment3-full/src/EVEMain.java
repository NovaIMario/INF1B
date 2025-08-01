import java.util.*;

public class EVEMain {
    public static String[] manufacturer = {"ACME", "Consolidated Products", "Goliath Inc."};
    public static String[] season = {"winter", "summer", "all-weather"};
    public static String[] motorFields = {"vehicle_id", "color", "cost", "engine", "manufacturer", "type", "valid", "wheels"};
    public static String[] colors = {"red","green","blue","orange","yellow","pink","purple", "white", "silver", "gold", "black"};
    public static String[] car = {"hatchback", "sedan", "convertible"};
    public static String[] truck = {"pickup", "eighteen wheeler"};
    public static String[] motorcycle = {"sport", "touring"};

    public static String[] seaFields = {"vehicle_id", "color", "cost", "engine", "manufacturer", "sails", "type",  "valid"};
    public static String[] sailingVessel = {"frigate", "schooner", "xebec"};
    public static String[] poweredVessel = {"jetski", "yacht", "cargo ship"};
    public static String[] material = {"canvas", "nylon", "mylar"};

    public static Map<String,List<List<String>>> init(String[] fields){
        Map<String,List<List<String>>> hash = new LinkedHashMap<>();
        for(String field: fields){
            hash.put(field, new ArrayList<>());
        }
        return hash;
    }//initialize linked hashmap with fields
    public static Map<String,Integer> track(String[] fields){
        Map<String,Integer> hash = new LinkedHashMap<>();
        for(String field: fields){
            hash.put(field, 0);
        }
        return hash;
    }// a hashmap that tracks how often a field appears
    public static String field(String information){
        String[] info = information.split(":");
        return info[0].trim();
    }//get field from input
    public static List<String> fieldVal(String information){
        String[] info = information.split(":");
        String[] values = info[1].split(",");
        List<String> trimmed = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            if(values[i].contains("0")){
                values[i] = ""+Integer.parseInt(values[i].trim());
            }
            trimmed.add(values[i].trim());
        }
        return trimmed;
    }//get field value from input
    public static Map<String,List<List<String>>> transform(List<String> array, String[] fields){
        Map<String,List<List<String>>> hash=init(fields);
        Map<String, Integer> track = track(fields);
        List<String> costList = new ArrayList<>();
        costList.add("0");//base value for incorrect value for cost (and vehicle_id)
        List<String> validList = new ArrayList<>();
        validList.add("false");//base value for incorrect value for valid
        List<String> emptyList = new ArrayList<>();
        emptyList.add("");//base value for incorrect value for everything else
        for (String information: array){//loop through each input line
            if(information.isEmpty()&&track.containsValue(1)){//check which fields does not have a value
                for(String key: track.keySet()){//add base value for missing fields
                    if(track.get(key)==0){
                        if(key.equals("cost")||key.equals("vehicle_id")){
                            hash.get(key).add(new ArrayList<>(costList));
                        }
                        else if(key.equals("valid")){
                            hash.get(key).add(new ArrayList<>(validList));
                        }
                        else{
                            hash.get(key).add(new ArrayList<>(emptyList));
                        }
                    }
                }
                track = track(fields);// reset track for the next fields
            }
            if(!information.isEmpty()){//line with information
                if(track.get(field(information))!=null) {//check if field is one of the valid ones
                    if (track.get(field(information)) == 0) {// if it is not a repeated field add the field value to the hashmap
                        if(field(information).equals("vehicle_id")){
                            String num = "0";//base value for vehicle
                            for(String str: fieldVal(information)){
                                if(str.matches("\\d+")){// if field value has a digit, use that. Else use default 0
                                    num = str;
                                }
                            }
                            List<String> veh = new ArrayList<>();
                            veh.add(num);//add to hashmap
                            track.put(field(information), 1);
                            hash.get(field(information)).add(veh);
                        }
                        else{//anything else just add it
                            track.put(field(information), 1);//increment track so that it records and discard multiples
                            hash.get(field(information)).add(fieldVal(information));
                        }
                    }
                }
            }
        }
        if(track.containsValue(1)) {
            for (String key : track.keySet()) {
                if (track.get(key) == 0) {
                    if (key.equals("cost") || key.equals("vehicle_id")) {
                        hash.get(key).add(new ArrayList<>(costList));
                    } else if (key.equals("valid")) {
                        hash.get(key).add(new ArrayList<>(validList));
                    } else {
                        hash.get(key).add(new ArrayList<>(emptyList));
                    }
                }//same procedure for missing fields after all the input
            }
        }
        return hash;
    }//transform array to hashmap
    public static void printNice(Map<String,List<List<String>>>hash){
        if(hash.get("vehicle_id")!=null) {
            int n = hash.get("vehicle_id").size();
            Integer[] ordId = new Integer[n];//order the vehicle_id and then find the index of the ordered one so that the list is ordered from lowest to highest vehicle_id  when printed
            Integer[] id = new Integer[n];
            Integer[] index = new Integer[n];
            for (int i = 0; i < n; i++) {
                ordId[i] = Integer.parseInt(hash.get("vehicle_id").get(i).get(0));
            }
            Arrays.sort(ordId);
            for (int i = 0; i < n; i++) {
                id[i] = Integer.parseInt(hash.get("vehicle_id").get(i).get(0));
            }
            for (int i = 0; i < n; i++) {
                index[i] = Arrays.asList(id).indexOf(ordId[i]);
            }
            for (int i : index) {
                for (String key : hash.keySet()) {
                    if (!hash.get(key).get(i).isEmpty()) {
                        String val = String.join(", ", hash.get(key).get(i));
                        System.out.println(key + ": " + val);
                    }
                }
                System.out.println();
            }
        }
    }//print hashmap


    public static boolean color(Map<String,List<List<String>>> hash, int n){
        if(hash.get("color").get(n).size()!=1){
            return false;
        }
        return Arrays.asList(colors).contains(hash.get("color").get(n).get(0));
    }//check valid color
    public static boolean manufacturer(Map<String,List<List<String>>> hash, int n){
        if(hash.get("manufacturer").get(n).size()!=1){
            return false;
        }
        return Arrays.asList(manufacturer).contains(hash.get("manufacturer").get(n).get(0));
    }//check valid manufacturer
    public static boolean type(Map<String,List<List<String>>> hash, int n){
        if(hash.get("type").get(n).size()!=2){
            return false;
        }
        if(hash.get("type").get(n).get(0).equals("car")){
            return Arrays.asList(car).contains(hash.get("type").get(n).get(1));
        }
        else if(hash.get("type").get(n).get(0).equals("truck")){
            return Arrays.asList(truck).contains(hash.get("type").get(n).get(1));
        }
        else if(hash.get("type").get(n).get(0).equals("motorcycle")){
            return Arrays.asList(motorcycle).contains(hash.get("type").get(n).get(1));
        }
        return false;
    }//check valid type
    public static boolean engine(Map<String,List<List<String>>> hash, int n){
        if(hash.get("engine").get(n).size()!=2){
            return false;
        }
        if(!Arrays.asList(manufacturer).contains(hash.get("engine").get(n).get(0))){
            return false;
        }
        if(hash.get("type").get(n).get(0).equals("car")){
            return hash.get("engine").get(n).get(1).equals("electric")||hash.get("engine").get(n).get(1).equals("diesel")||hash.get("engine").get(n).get(1).equals("petrol");
        }
        else if(hash.get("type").get(n).get(0).equals("truck")){
            return hash.get("engine").get(n).get(1).equals("diesel")||hash.get("engine").get(n).get(1).equals("petrol");
        }
        else if(hash.get("type").get(n).get(0).equals("motorcycle")){
            return hash.get("engine").get(n).get(1).equals("petrol");
        }
        return false;
    }//check valid engine
    public static boolean wheels(Map<String,List<List<String>>> hash, int n){
        if(hash.get("wheels").get(n).size()!=3){
            return false;
        }
        if(!Arrays.asList(manufacturer).contains(hash.get("wheels").get(n).get(0))){
            return false;
        }
        if(!Arrays.asList(season).contains(hash.get("wheels").get(n).get(1))){
            return false;
        }
        if(hash.get("type").get(n).get(0).equals("car")){
            return hash.get("wheels").get(n).get(2).equals("4");
        }
        else if(hash.get("type").get(n).get(1).equals("pickup")){
            return hash.get("wheels").get(n).get(2).equals("6")||hash.get("wheels").get(n).get(2).equals("6");
        }
        else if(hash.get("type").get(n).get(1).equals("eighteen wheeler")){
            return hash.get("wheels").get(n).get(2).equals("18");
        }
        else if(hash.get("type").get(n).get(0).equals("motorcycle")){
            return hash.get("wheels").get(n).get(2).equals("2");
        }
        return false;
    }//check valid wheels
    public static Map<String,List<List<String>>> validHash (Map<String,List<List<String>>>hash){
        int n = hash.get("vehicle_id").size();
        for (int i = 0; i < n; i++) {
            if(color(hash,i)&&manufacturer(hash,i)&&type(hash,i)&&engine(hash,i)&&wheels(hash,i)&&addVal(hash,i)) {
                hash.get("valid").get(i).set(0,"true");
            }
        }
        return hash;
    }//update if the valid field should be true or false
    public static int wheelsCost(Map<String,List<List<String>>> hash, int n){
        int wheelType= 0;
        if(hash.get("wheels").get(n).contains("summer")){
            wheelType=100;
        }
        if(hash.get("wheels").get(n).contains("winter")){
            wheelType=120;
        }
        if(hash.get("wheels").get(n).contains("all-weather")){
            wheelType=150;
        }
        int value = 0;
        for(String str:hash.get("wheels").get(n)){
            if (str.matches("\\d+")){
                value = Integer.parseInt(str);
            }
        }
        return value*wheelType;
    }//calculate wheels cost
    public static int vehicleCost(Map<String,List<List<String>>> hash, int n){
        if(hash.get("type").get(n).contains("hatchback")){
            return 8000;
        }
        if(hash.get("type").get(n).contains("sedan")){
            return 12000;
        }
        if(hash.get("type").get(n).contains("convertible")){
            return 20000;
        }
        if(hash.get("type").get(n).contains("sport")){
            return 16000;
        }
        if(hash.get("type").get(n).contains("touring")){
            return 9000;
        }
        if(hash.get("type").get(n).contains("pickup")){
            return 20000;
        }
        if(hash.get("type").get(n).contains("eighteen wheeler")){
            return 35000;
        }

        return 0;
    }//calculate vehicle cost
    public static int engineCost(Map<String,List<List<String>>> hash, int n){
        if(hash.get("engine").get(n).contains("electric")){
            return 5000;
        }
        if(hash.get("engine").get(n).contains("petrol")){
            return 1000;
        }
        if(hash.get("engine").get(n).contains("diesel")){
            return 2000;
        }
        return 0;
    }//calculate engine cost
    public static int charge(Map<String,List<List<String>>> hash, int n){
        int charge = 0;
        if(hash.get("cost").isEmpty()){
            return 0;
        }
        if(hash.get("type").get(n).contains("car")&&hash.get("manufacturer").get(n).contains("ACME")&&hash.get("wheels").get(n).contains("ACME")){
            charge -=871;
        }
        if(hash.get("manufacturer").get(n).contains("Consolidated Products")&&hash.get("wheels").get(n).contains("summer")){
            charge +=145;
        }
        if(hash.get("manufacturer").get(n).contains("Goliath Inc.")&&hash.get("engine").get(n).contains("Goliath Inc.")&&hash.get("type").get(n).contains("car")){
            charge -=1219;
        }
        if(hash.get("type").get(n).contains("car")&&hash.get("engine").get(n).contains("electric")){
            charge -=760;
        }
        return charge;
    }
    public static Map<String,List<List<String>>> costHash (Map<String,List<List<String>>>hash){
        int n = hash.get("vehicle_id").size();
        for (int i = 0; i < n; i++) {
            hash.get("cost").get(i).set(0, ""+(wheelsCost(hash,i)+vehicleCost(hash,i)+engineCost(hash,i)+charge(hash,i)));
        }
        return hash;
    }// update final cost of the input


    public static boolean addVal(Map<String,List<List<String>>> hash, int n){
        if(hash.get("type").get(n).get(1).equals("schooner")&&hash.get("color").get(n).get(0).equals("purple")){
            return false;
        }
        if(hash.get("type").get(n).contains("cargo ship")&&!hash.get("manufacturer").get(n).contains("Consolidated Products")){
            return false;
        }
        if(hash.get("type").get(n).contains("xebec")&&!hash.get("manufacturer").get(n).contains("Goliath Inc.")){
            return false;
        }
        if(hash.get("wheels")!=null){
            if(hash.get("wheels").get(n).contains("Consolidated Products")&&hash.get("type").get(n).contains("motorcycle")){
                return false;
            }
        }
        if(hash.get("type").get(n).contains("eighteen wheeler")&&!hash.get("wheels").get(n).contains("winter")){
            return false;
        }
        if(hash.get("type").get(n).contains("jetski")&&!hash.get("color").get(n).contains("blue")){
            return false;
        }
        if(hash.get("type").get(n).contains("sedan")&&hash.get("color").get(n).contains("orange")){
            return false;
        }
        if(hash.get("type").get(n).contains("pickup")&&hash.get("manufacturer").get(n).contains("Goliath Inc.")&&hash.get("color").get(n).get(0).equals("red")){
            return false;
        }
        return true;
    }
    public static int seaCharge(Map<String,List<List<String>>> hash, int n){
        int charge = 0;
        if(hash.get("cost").isEmpty()){
            return 0;
        }
        if(hash.get("type").get(n).contains("schooner")&&hash.get("sails").get(n).contains("mylar")){
            charge -=2657;
        }
        if(hash.get("type").get(n).contains("cargo ship")&&hash.get("manufacturer").get(n).contains("ACME")){
            charge -=10165;
        }
        if(hash.get("type").get(n).contains("powered vessel")&&hash.get("engine").get(n).contains("petrol")){
            charge -=804;
        }
        if(hash.get("type").get(n).contains("jetski")&&(hash.get("color").get(n).contains("red")||hash.get("color").get(n).contains("silver")||hash.get("color").get(n).contains("purple"))){
            charge -=352;
        }
        return charge;
    }
    public static boolean seaType(Map<String,List<List<String>>> hash, int n){
        if(hash.get("type").get(n).size()!=2){
            return false;
        }
        if(hash.get("type").get(n).get(0).equals("sailing vessel")){
            return Arrays.asList(sailingVessel).contains(hash.get("type").get(n).get(1));
        }
        else if(hash.get("type").get(n).get(0).equals("powered vessel")){
            return Arrays.asList(poweredVessel).contains(hash.get("type").get(n).get(1));
        }
        return false;
    }
    public static boolean seaEngine(Map<String,List<List<String>>> hash, int n){
        if(hash.get("type").get(n).contains("sailing vessel")){
            return true;
        }
        if(hash.get("engine").get(n).size()!=2){
            return false;
        }
        if(!Arrays.asList(manufacturer).contains(hash.get("engine").get(n).get(0))){
            return false;
        }
        if(hash.get("type").get(n).get(1).equals("jetski")){
            return hash.get("engine").get(n).get(1).equals("petrol");
        }
        else if(hash.get("type").get(n).get(1).equals("yacht")){
            return hash.get("engine").get(n).get(1).equals("diesel")||hash.get("engine").get(n).get(1).equals("petrol");
        }
        else if(hash.get("type").get(n).get(1).equals("cargo ship")){
            return hash.get("engine").get(n).get(1).equals("diesel");
        }
        return false;
    }
    public static boolean sails(Map<String,List<List<String>>> hash, int n){
        if(hash.get("type").get(n).contains("powered vessel")){
            return true;
        }
        if(hash.get("sails").get(n).size()!=3){
            return false;
        }
        if(!Arrays.asList(manufacturer).contains(hash.get("sails").get(n).get(0))){
            return false;
        }
        if(!Arrays.asList(material).contains(hash.get("sails").get(n).get(1))){
            return false;
        }
        if(hash.get("type").get(n).contains("frigates")){
            return hash.get("sails").get(n).get(2).equals("10");
        }
        else if(hash.get("type").get(n).contains("schooner")){
            return hash.get("sails").get(n).get(2).equals("6");
        }
        else if(hash.get("type").get(n).contains("xebec")){
            return hash.get("sails").get(n).get(2).equals("3");
        }
        return false;
    }
    public static Map<String,List<List<String>>> validSeaHash (Map<String,List<List<String>>>hash){
        int n = hash.get("vehicle_id").size();
        for (int i = 0; i < n; i++) {
            if(color(hash,i)&&manufacturer(hash,i)&&seaType(hash,i)&&seaEngine(hash,i)&&sails(hash,i)&&addVal(hash,i)) {
                hash.get("valid").get(i).set(0,"true");
            }
            else{
                hash.get("valid").get(i).set(0,"false");
            }
        }
        return hash;
    }
    public static int sailCost(Map<String,List<List<String>>> hash, int n){
        int sailType= 0;
        if(hash.get("sails").get(n).contains("canvas")){
            sailType=500;
        }
        if(hash.get("sails").get(n).contains("nylon")){
            sailType=350;
        }
        if(hash.get("sails").get(n).contains("mylar")){
            sailType=900;
        }
        int value = 0;
        for(String str:hash.get("sails").get(n)){
            if (str.matches("\\d+")){
                value = Integer.parseInt(str);
            }
        }
        return value*sailType;
    }
    public static int seaVehicleCost(Map<String,List<List<String>>> hash, int n){
        if(hash.get("type").get(n).contains("frigate")){
            return 100000;
        }
        if(hash.get("type").get(n).contains("xebec")){
            return 5000;
        }
        if(hash.get("type").get(n).contains("schooner")){
            return 10000;
        }
        if(hash.get("type").get(n).contains("jetski")){
            return 1000;
        }
        if(hash.get("type").get(n).contains("yacht")){
            return 50000;
        }
        if(hash.get("type").get(n).contains("cargo ship")){
            return 100000;
        }
        return 0;
    }
    public static Map<String,List<List<String>>> costSeaHash (Map<String,List<List<String>>>hash){
        int n = hash.get("vehicle_id").size();
        for (int i = 0; i < n; i++) {
            hash.get("cost").get(i).set(0, ""+(sailCost(hash,i)+seaVehicleCost(hash,i)+engineCost(hash,i)+seaCharge(hash,i)));
        }
        return hash;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> array = new ArrayList<>();
        while(scanner.hasNextLine()){
            String input = scanner.nextLine();
            if(input.equals(" ")){
                break;
            }
            array.add(input);
        }
        boolean isSea = false;
        for(String str: array){
            if (str.contains("powered vessel")||str.contains("sailing vessel")||str.contains("sails")) {
                isSea = true;
                break;
            }
        }
        Map<String, List<List<String>>> hash;
        if(isSea){
            hash = (transform(array, seaFields));
            printNice(costSeaHash(validSeaHash(hash)));
        }
        else{
            hash = (transform(array, motorFields));
            printNice(costHash(validHash(hash)));
        }

    }//receive input
}