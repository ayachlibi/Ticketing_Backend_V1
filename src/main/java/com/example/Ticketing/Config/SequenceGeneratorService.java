package com.example.Ticketing.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.CodecRegistryProvider;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;

import javax.management.Query;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service

public class SequenceGeneratorService {

    @Autowired

    private MongoOperations mongoOperations;

    public long generateSequence (String seqName){

        database_sequences  counter =
        mongoOperations.findAndModify(query(where("id").is(seqName)),
                new Update().inc("seq",1),
                options().returnNew(true).upsert(true),
                database_sequences.class);

        return  !Objects.isNull(counter) ? counter.getSeq() :1;
    }

}
