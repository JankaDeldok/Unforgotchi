package com.jlp.unforgotchi.detaillist

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jlp.unforgotchi.R
import com.jlp.unforgotchi.db.ReminderList
import com.jlp.unforgotchi.db.ReminderListElement
import com.jlp.unforgotchi.db.ReminderListElementViewModel

class DetailList : AppCompatActivity(), DetailListsAdapter.OnItemClickListener {

    //lateinit var toggle : ActionBarDrawerToggle
    private var arrayListNames = emptyArray<String>()
    private var arrayListNumber = emptyArray<Int>()

    private lateinit var mUserViewModel: ReminderListElementViewModel
    private val adapter = DetailListsAdapter(/*arrayList,*/ this)
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_list_view)

        //val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        //val navView : NavigationView = findViewById(R.id.nav_view)

        val bundle: Bundle? = intent.extras
        position = intent.getIntExtra("position", 0)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.lists_recycler_view)
        // this grid layout holds all the cards with the saved locations:
        recyclerview.layoutManager = LinearLayoutManager(this)
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
        recyclerview.setHasFixedSize(true)


        mUserViewModel = ViewModelProvider(this).get(ReminderListElementViewModel::class.java)
        if(position==0){
            mUserViewModel.readAllElementsFromList1.observe(this, Observer { reminderListElement ->
                adapter.setData(reminderListElement)
            })
        }
        if(position==1){
            mUserViewModel.readAllElementsFromList2.observe(this, Observer { reminderListElement ->
                adapter.setData(reminderListElement)
            })
        }
        if(position==2){
            mUserViewModel.readAllElementsFromList3.observe(this, Observer { reminderListElement ->
                adapter.setData(reminderListElement)
            })
        }
        if(position==3){
            mUserViewModel.readAllElementsFromList4.observe(this, Observer { reminderListElement ->
                adapter.setData(reminderListElement)
            })
        }
        if(position==4){
            mUserViewModel.readAllElementsFromList5.observe(this, Observer { reminderListElement ->
                adapter.setData(reminderListElement)
            })
        }
        if(position==5){
            mUserViewModel.readAllElementsFromList6.observe(this, Observer { reminderListElement ->
                adapter.setData(reminderListElement)
            })
        }
        if(position==6){
            mUserViewModel.readAllElementsFromList7.observe(this, Observer { reminderListElement ->
                adapter.setData(reminderListElement)
            })
        }
        if(position==7){
            mUserViewModel.readAllElementsFromList8.observe(this, Observer { reminderListElement ->
                adapter.setData(reminderListElement)
            })
        }
        if(position==8){
            mUserViewModel.readAllElementsFromList9.observe(this, Observer { reminderListElement ->
                adapter.setData(reminderListElement)
            })
        }
        if(position==9){
            mUserViewModel.readAllElementsFromList10.observe(this, Observer { reminderListElement ->
                adapter.setData(reminderListElement)
            })
        }


        //when clicking the add-button:
        val addItemButton: View = findViewById(R.id.add_item_button)
        addItemButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.add_listname_layout, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.newListName)

            with(builder) {
                setTitle("New List Item")
                setPositiveButton("OK"){ dialog, which ->
                    insertListItem(editText.text.toString())
                }
                setNegativeButton("Cancel"){ dialog, which ->
                    Toast.makeText(applicationContext, "Cancel button clicked", Toast.LENGTH_SHORT).show()
                }
                setView(dialogLayout)
                show()
            }
        }

        //all down here for the navigation menu
        /*
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //set intents
        val homePage = Intent(this@DetailList, MainActivity::class.java)
        val settingPage = Intent(this@DetailList, Settings::class.java)
        val listsPage = Intent(this@DetailList, Lists::class.java)
        val locationsPage = Intent(this@DetailList, Locations::class.java)



        navView.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.nav_home -> startActivity(homePage)
                R.id.nav_lists -> startActivity(listsPage)
                R.id.nav_locations -> startActivity(locationsPage)
                R.id.nav_trash -> Toast.makeText(
                    applicationContext,
                    "Clicked placeholder",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_settings -> startActivity(settingPage)
                R.id.nav_login -> Toast.makeText(
                    applicationContext,
                    "Clicked placeholder",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_share -> Toast.makeText(
                    applicationContext,
                    "Clicked placeholder",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_rate_us -> Toast.makeText(
                    applicationContext,
                    "Clicked placeholder",
                    Toast.LENGTH_SHORT
                ).show()

            }

            true

        }
        */

        val item = object : SwipeToDelete(this, 0, ItemTouchHelper.LEFT) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deleteList(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper=ItemTouchHelper(item)
        itemTouchHelper.attachToRecyclerView(recyclerview)
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }*/

    private fun insertListItem(listElementName: String){
        if (!inputCheck(listElementName)){
            Toast.makeText(
                applicationContext,
                "Please give input to create a list",
                Toast.LENGTH_SHORT
            ).show()
        }
        else {
            // Create User Object
            val reminderListElement = ReminderListElement(0, listElementName, position)
            // Add Data to Database
            mUserViewModel.addReminderListElement(reminderListElement)
            arrayListNames += listElementName
            arrayListNumber += position
            //arrayList.add(reminderList)
            //mUserViewModel.getCount.observe(this, Observer<Int> { integer -> index = integer })
            Toast.makeText(applicationContext, "Successfully added!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(listName: String): Boolean{
        return !(TextUtils.isEmpty(listName))
    }

    private fun deleteList(positionItem: Int){
        // Create User Object
        val reminderListElement = ReminderListElement(
            positionItem,
            arrayListNames[positionItem],
            arrayListNumber[positionItem]
        )
        // Remove from Database
        mUserViewModel.deleteReminderListElement(reminderListElement)
        arrayListNames.drop(positionItem)
        arrayListNumber.drop(positionItem)
        Toast.makeText(applicationContext, "Successfully removed!", Toast.LENGTH_LONG).show()
    }

    private fun editList(listElementName: String, position: Int) {
        if (!inputCheck(listElementName)) {
            Toast.makeText(
                applicationContext,
                "Please give input to change the name",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            // Create Reminder List Element Object
            val updatedReminderListElement = ReminderListElement(
                position,
                listElementName,
                R.drawable.ic_baseline_list_alt_24
            )
            // Update Current Object
            mUserViewModel.updateReminderListElement(updatedReminderListElement)
            arrayListNames[position] = listElementName
            Toast.makeText(
                applicationContext,
                "Successfully updated!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()

        //open textDialog to adapt name
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.add_listname_layout, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.newListName)

        with(builder) {
            setTitle("Change Items Name")
            setPositiveButton("OK") { _, _ ->
                editList(editText.text.toString(), position)
            }
            setNegativeButton("Cancel"){ _, _ ->
                Toast.makeText(applicationContext, "Cancel button clicked", Toast.LENGTH_SHORT).show()
            }
            setView(dialogLayout)
            show()
        }
    }
}