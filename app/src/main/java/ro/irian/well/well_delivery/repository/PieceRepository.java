package ro.irian.well.well_delivery.repository;

import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;
import javax.inject.Singleton;

import ro.irian.well.well_delivery.domain.Piece;

@Singleton
public class PieceRepository extends FirebaseRepository<Piece> {

    private static final String TAG = "PieceRepository";

    @Inject
    public PieceRepository(FirebaseFirestore firebaseFirestore) {
        super(firebaseFirestore.collection("pieces"), Piece.class);
    }

}
