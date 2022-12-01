package com.example.fhir_etl_test;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class SetupDataSource {

    public SQLServerDataSource setSQL(JsonNode dataObj) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(dataObj.findValue("username").asText());
        ds.setPassword(dataObj.findValue("password").asText());
        ds.setServerName("localhost\\\\" + dataObj.findValue("servername").asText() + ";");
        ds.setPortNumber(Integer.parseInt(dataObj.findValue("portnumber").asText()));
        if (dataObj.findValue("databasename").asText() != "")
            ds.setDatabaseName(dataObj.findValue("databasename").asText());
        else
            ds.setDatabaseName("master");
        ds.setTrustServerCertificate(true);

        return ds;
    }

    public boolean setSession(JsonNode dataObj,HttpSession session) {
        try{
            session.setAttribute("dataObj", dataObj.toString());
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public JsonNode getSession_JsonNode(HttpSession session) throws JsonMappingException, JsonProcessingException{
        String dataObj_Str =  session.getAttribute("dataObj").toString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObj = mapper.readTree(dataObj_Str);
        return dataObj;
    }
}
