package com.example.android.politicalpreparedness.util

import com.example.android.politicalpreparedness.data.data_objects.dto.ELECTION_DTO
import com.example.android.politicalpreparedness.data.data_objects.dto.ELECTION_DTO_Container


fun parseElectionsToContainer(list: List<ELECTION_DTO>): ELECTION_DTO_Container {
    val auxList = ArrayList<ELECTION_DTO>()
    for (element in list) {
        auxList.add(element)
    }
    return ELECTION_DTO_Container(auxList)
}