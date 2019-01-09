package com.example.molys.mediumuserviewapplication.items

import com.example.molys.mediumuserviewapplication.MediumCreator
import com.example.molys.mediumuserviewapplication.data.Publication

class PublicationItem(
  publication: Publication
) : BaseItem(MediumCreator.TYPE_PUBLICATION){
    var name : String = publication.name
    var description : String = publication.description
    var url : String = publication.url
    var imageUrl : String = publication.imageUrl
}