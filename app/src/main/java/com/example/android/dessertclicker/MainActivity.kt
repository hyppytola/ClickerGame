package com.example.clickerapp

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.example.android.dessertclicker.Enemy
import com.example.android.dessertclicker.MainActivity2
import com.example.android.dessertclicker.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val allEnemies = listOf(
        Enemy(100, R.drawable.enemy1_idle, R.drawable.enemy1_death, 350 + 800),
        Enemy(200,R.drawable.enemy2_idle, R.drawable.enemy2_death, 350 + 800),
        Enemy(300,R.drawable.enemy3_idle, R.drawable.enemy3_death, 350 + 800),
        Enemy(400,R.drawable.enemy4_idle, R.drawable.enemy3_death, 350 + 800)
    )

    private var currentEnemy = 0
    private val numEnemy = allEnemies.size

    private var mAnimationDrawable: AnimationDrawable? = null
    private var mAnimationEnemyDrawable: AnimationDrawable? = null

    private var enemyHP = allEnemies[currentEnemy].health_point

    private var mc_damage = 20
    private var bonusDamage = 0

    private var countForBonusDamage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("MyActivity","onCreate Called")
        setContentView(R.layout.activity_main)

        progressBar.max = enemyHP
        progressBar.setProgress(enemyHP)


        setMCIdleAnim()
        setEnemyIdleAnim()

        attackBtn.setOnClickListener {
            attack()
        }

        bonusDamageBtn.setOnClickListener {
            addBonusDamage()
        }

    }

    fun attack() {
        enemyHP -= mc_damage + bonusDamage
        if (enemyHP <= 0) {
            enemyHP = 0
        }
        if (bonusDamage > 0) {
            countForBonusDamage = 0
            bonusDamage = 0
        }
        progressBar.setProgress(enemyHP)

//        enemy_HP.setText(enemyHP.toString())
//        MC_Damage.setText((mc_damage + bonusDamage).toString())


        checkHP()

        mAnimationDrawable?.stop()
//        enemyView.setImageResource(R.color.hurt)
        main_hero.setBackgroundResource(R.drawable.main_hero_atack)
        mAnimationDrawable = main_hero.getBackground() as AnimationDrawable?
        mAnimationDrawable?.start()
        val handler = Handler()
        handler.postDelayed({
            enemyView.setImageResource(R.color.transparent)
            setMCIdleAnim()
        }, 400)

        countForBonusDamage++
        if (countForBonusDamage == 5) {
            bonusDamageBtn.visibility = View.VISIBLE
        }
    }

    fun addBonusDamage() {
        bonusDamage = 50
        bonusDamageBtn.visibility = View.INVISIBLE
//        MC_Damage.setText((mc_damage + bonusDamage).toString())
    }

    fun checkHP() {
        if (enemyHP <= 0) {
            Log.i("MyActivity","Enemy Destroyed")
            dieAnim()
            val handler = Handler()
            handler.postDelayed({
                changeEnemy()
            }, allEnemies[currentEnemy].dead.toLong())

        }
    }

    fun dieAnim() {
        mAnimationEnemyDrawable?.stop()
        enemyView.setBackgroundResource(allEnemies[currentEnemy].enemy_death)
        mAnimationEnemyDrawable = enemyView.getBackground() as AnimationDrawable?
        mAnimationEnemyDrawable?.start()
    }

    fun changeEnemy() {
        mAnimationEnemyDrawable?.stop()
        currentEnemy++
        if (currentEnemy === numEnemy) {
            val intent : Intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        } else {
            enemyHP = allEnemies[currentEnemy].health_point
            progressBar.max = enemyHP
            progressBar.setProgress(enemyHP)
//            enemy_HP.setText(enemyHP.toString())
            setEnemyIdleAnim()
        }
    }

    fun setMCIdleAnim() {
        mAnimationDrawable?.stop()
        main_hero.setBackgroundResource(R.drawable.main_hero_idle)
        mAnimationDrawable = main_hero.getBackground() as AnimationDrawable?
        mAnimationDrawable?.start()
    }

    fun setEnemyIdleAnim() {
        mAnimationEnemyDrawable?.stop()
        enemyView.setBackgroundResource(allEnemies[currentEnemy].enemy_idle)
        mAnimationEnemyDrawable = enemyView.getBackground() as AnimationDrawable?
        mAnimationEnemyDrawable?.start()
    }
}