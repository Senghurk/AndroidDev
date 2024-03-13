package com.csx4109.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import java.time.LocalDateTime

class FirebaseActivity : AppCompatActivity() {

    data class FirebaseStudent(
        val studentId: Int? = null,
        val name: String? = null,
        val grade: Double? = null
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)

        val database = Firebase.database("https://fragmentdemo-e9834-default-rtdb.asia-southeast1.firebasedatabase.app/")
        val messageRef = database.getReference("message")
        val studentRef = database.getReference("stundets").child("1")

        messageRef.setValue("Now it's ${LocalDateTime.now()}")
        messageRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val message = snapshot.value.toString()

                Toast.makeText(this@FirebaseActivity, message, Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {}

        })

        studentRef.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val student = snapshot.getValue(FirebaseStudent::class.java)

                Toast.makeText(this@FirebaseActivity, "My name is ${student?.name}", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }
}