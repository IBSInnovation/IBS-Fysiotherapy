package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.api.services.storage.Storage;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import org.ibs.application.IExerciseService;
import org.ibs.application.dto.exercisedto.AskAllExercise;
import org.ibs.application.dto.exercisedto.AskExercise;
import org.ibs.application.dto.exercisedto.GetExercise;
import org.ibs.application.dto.exercisedto.SaveExercise;
import org.ibs.application.dto.patientdto.GetPatient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class ExerciseService implements IExerciseService {
    private final Firestore db;

    public ExerciseService() {
        this.db = FirestoreClient.getFirestore();
    }

    /**
     * Searches the database for an Exercise entity with the given id and returns it if it exists.
     * @param askExercise
     * @return Exercise of given id
     * @throws Exception
     */
    @Override
    public GetExercise getById(AskExercise askExercise) throws Exception {
        try {
            DocumentReference documentReference = db
                    .collection("exercises")
                    .document(askExercise.categoryId);

            DocumentSnapshot document = documentReference.get().get();

            if (document.exists()) {
                GetExercise dto = document.toObject(GetExercise.class);

                // TODO: hier kijken naar hoe er met Id vanuit firestore omgegaan word
                dto.id = document.getId();


                return dto;
            }

            else {
                throw new Exception("Document doesn't exist");
            }
        } catch (Exception e) {
            throw new Exception("Exercise could not be found due to an error", e);
        }
    }


    /**
     * Saves and updates the given Exercise entity in the database.
     * @param saveExercise
     * @return The saved Exercise entity
     * @throws Exception
     */
//    TODO: zorg ervoor dat het ook in category word geupdate
    @Override
    public GetExercise saveExercise(SaveExercise saveExercise) throws Exception {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("name", saveExercise.name);
            data.put("description", saveExercise.description);
            data.put("category", saveExercise.categoryId);

            ApiFuture<DocumentReference> addedDocRef = db.collection("exercises").add(data);


            return new GetExercise(
                    addedDocRef.get().getId(),
                    saveExercise.name,
                    saveExercise.description,
                    saveExercise.categoryId
            );

        } catch (Exception e) {
            throw new Exception("Exercise was not persisted due to an error", e);
        }
    }

    /**
     * Deletes the Exercise entity with the given id.
     * @param askExercise
     * @return true if the operation succeeded
     * @throws Exception
     */
    @Override
    public boolean deleteExercise(AskExercise askExercise) throws Exception {
        try {
            ApiFuture<WriteResult> writeResult = db
                    .collection("exercises")
                    .document(askExercise.id)
                    .delete();

            ApiFuture<WriteResult> writeResult2 = db
                    .collection("category")
                    .document(askExercise.categoryId)
                    .collection("exercises")
                    .document(askExercise.id)
                    .delete();

            writeResult.get().getUpdateTime().toString();
            return true;
        } catch (Exception e) {
            throw new Exception("Exercise could not be deleted due to an error", e);
        }
    }
}
