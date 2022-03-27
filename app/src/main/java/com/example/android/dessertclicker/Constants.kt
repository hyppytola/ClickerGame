package com.example.android.dessertclicker

object Constants {



    fun getEnemy(): ArrayList<Enemy> {
        val enemyList = ArrayList<Enemy>()

        val enemy1 = Enemy(
            50,
            R.drawable.enemy1_idle1,
            R.drawable.enemy1_death_1,
            250 + 400
        )

        enemyList.add(enemy1)

        val enemy2 = Enemy(
            100,
            R.drawable.enemy2_idle_1,
            R.drawable.enemy2_death_1,
            250 + 400
        )

        enemyList.add(enemy2)

        val enemy3 = Enemy(
            150,
            R.drawable.enemy3_idle_1,
            R.drawable.enemy3_death_1,
            250 + 400
        )

        enemyList.add(enemy3)

        val enemy4 = Enemy(
            200,
            R.drawable.enemy4_idle_1,
            R.drawable.enemy4_death_1,
            250 + 400
        )

        enemyList.add(enemy4)

        val enemy5 = Enemy(
            250,
            R.drawable.enemy5_idle_1,
            R.drawable.enemy5_death_1,
            250 + 400
        )

        enemyList.add(enemy5)

        return enemyList
    }
}