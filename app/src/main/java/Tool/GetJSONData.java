package Tool;

import org.json.JSONObject;

public class GetJSONData {
    public static String[] parseWithJSON(String response){
        String[] locationArray=new String[8];

        try {
            JSONObject jsonObject=new JSONObject(response);
            int status=jsonObject.getInt("Status");
            if(status==200){
                JSONObject location=jsonObject.getJSONObject("location");

                locationArray[0]=jsonObject.getString("name");
                locationArray[1]=jsonObject.getString("id");
                locationArray[2]=jsonObject.getString("lat");
                locationArray[3]=jsonObject.getString("lon");
                locationArray[4]=jsonObject.getString("adm2");
                locationArray[5]=jsonObject.getString("adm1");
                locationArray[6]=jsonObject.getString("country");
                locationArray[7]=jsonObject.getString("tz");

            }
        }catch (Exception e){
            e.printStackTrace();
        }return locationArray;
    }
}
