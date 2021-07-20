package com.example.android.politicalpreparedness.util

import com.example.android.politicalpreparedness.data.data_objects.dbo.ELECTION_DBO
import com.example.android.politicalpreparedness.data.data_objects.dto.ELECTION_DTO
import com.example.android.politicalpreparedness.data.data_objects.dto.ELECTION_DTO_Container
import org.json.JSONObject

fun parseElectionsJsonResult(jsonResult: JSONObject): ELECTION_DTO_Container {
    val electionObjectsInResponse = jsonResult.getJSONArray("elections")
    val electionList = ArrayList<ELECTION_DTO>()
    var i = 0
    while(i < electionObjectsInResponse.length()){
        electionList.add(electionObjectsInResponse[i] as ELECTION_DTO)
        i++
    }
    val electionListContainter = ELECTION_DTO_Container(electionList)
    return electionListContainter
}