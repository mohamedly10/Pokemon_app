package com.example.mycomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycomposeapp.model.PokemonDM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardList()
        }
    }
}

@Composable
fun PokemonCardItem(pokemonDM: PokemonDM, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .shadow(8.dp, shape = RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .background(
                color = cardColor(pokemonDM.type),
                        shape = RoundedCornerShape(15.dp)
            )



            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {
            Text(pokemonDM.name, color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    pokemonDM.type,
                    color = Color.White,
                    modifier = Modifier
                        .background(
                            color = typeColor(pokemonDM.type),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(8.dp),
                            fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Row { Text("Attack:  ",color = Color.White, )
                        Text(" ${pokemonDM.spAttack}",color = Color.Black,    fontWeight = FontWeight.Medium, ) }

                    Row { Text("Defense:  ",color = Color.White, )
                        Text("${pokemonDM.spDefense}",color = Color.Black,fontWeight = FontWeight.Medium ) }
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier.size(150.dp) // slightly bigger than image
                .background(
                    color = Color.White.copy(alpha = 0.25f),
                    shape = CircleShape

        ),
                    Alignment.Center
                ){
        Image(

            painter = painterResource(id =pokemonDM.icon),
            contentDescription = "Pokemon image",
            modifier = Modifier.size(120.dp)
        )
            }
    }
}

@Preview(showSystemUi = true)



@Composable
private fun CardList(){
val pokemonList = readData()
    LazyColumn(

                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    bottom = 50.dp // add space at bottom
                )

    ) {
        items(pokemonList.size){
            PokemonCardItem(pokemonList.get(it))
        }
    }


}
fun readData(): List< PokemonDM>{
 return listOf<PokemonDM>(
        PokemonDM(name = "Bulbasaur", type = "grass", spAttack = 65, spDefense = 65, icon = R.drawable.bulbasaur),
        PokemonDM(name = "Ivysaur", type = "grass", spAttack = 80, spDefense = 80, icon = R.drawable.ivysaur),
        PokemonDM(name = "Venusaur", type = "grass", spAttack = 122, spDefense = 120, icon = R.drawable.venusaur),
        PokemonDM(name = "Charmander", type = "fire", spAttack = 60, spDefense = 50, icon = R.drawable.charmander),
        PokemonDM(name = "Charmeleon", type = "fire", spAttack = 80, spDefense = 65, icon = R.drawable.charmeleon),
        PokemonDM(name = "Charizard", type = "fire", spAttack = 159, spDefense = 115, icon = R.drawable.charizard),
        PokemonDM(name = "Squirtle", type = "water", spAttack = 50, spDefense = 64, icon = R.drawable.squirtle),
        PokemonDM(name = "Wartortle", type = "water", spAttack = 65, spDefense = 80, icon = R.drawable.wartortle),
        PokemonDM(name = "Blastoise", type = "water", spAttack = 135, spDefense = 115, icon = R.drawable.blastoise),
    )

}
@Composable
fun cardColor(type:String): Color{
if (type=="grass"){

    return colorResource(id = R.color.grass_color)
}
    else if(type=="fire"){
        return colorResource(id = R.color.fire_color)
    }
    else{
        return  colorResource(id = R.color.water_color)
    }
}
@Composable
fun typeColor(type:String): Color{
    if (type=="grass"){

        return colorResource(id = R.color.type_g_color)
    }
    else if(type=="fire"){
        return colorResource(id = R.color.type_f_color)
    }
    else{
        return  colorResource(id = R.color.type_w_color)
    }
}