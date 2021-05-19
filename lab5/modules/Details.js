import React, { useState, useEffect } from 'react';
import { View, Dimensions, Text, StyleSheet, SafeAreaView, ScrollView, Image, ActivityIndicator } from 'react-native';
import { styleConfig } from "../style";

import { choosePoster } from "../assets/global/choosePoster";
import { tt } from "../assets/global/tt";

const window = Dimensions.get("window");
const screen = Dimensions.get("screen");



export default function Details({ route }) {

    const { id, title, year } = route.params;

    const data = tt(id)
    const [dimensions, setDimensions] = useState({ window, screen });

    const onChange = ({ window, screen }) => {
        setDimensions({ window, screen });
    };

    useEffect(() => {
        Dimensions.addEventListener("change", onChange);
        return () => {
            Dimensions.removeEventListener("change", onChange);
        };
    });



    
    const orientation = () => {
        const dim = Dimensions.get('screen');
        if (dim.height >= dim.width) {
            return styles
        } else {
            return landscape
        }
    }

    return (
        <>
            <SafeAreaView>
                <ScrollView style={{ backgroundColor: styleConfig.bg }}>
                    <View style={orientation().infoScreen}>
                        <View style={orientation().infoImageSection}>
                            <Image
                                style={orientation().infoImage}
                                source={choosePoster(data.Poster) }
                            />
                        </View>
                        <View style={orientation().infoScreenTextView}>
                            <Text style={orientation().titleText}>Title</Text>
                            <Text style={orientation().subText}>{data.Title != '' ? data.Title : title}</Text>

                            <Text style={orientation().titleText}>Runtime</Text>
                            <Text style={orientation().subText}>{data.Runtime}</Text>

                            <Text style={orientation().titleText}>Genre</Text>
                            <Text style={orientation().subText}>{data.Genre}</Text>

                            <Text style={orientation().titleText}>Awards</Text>
                            <Text style={orientation().subText}>{data.Awards}</Text>

                            <Text style={orientation().titleText}>Rating</Text>
                            <Text style={orientation().subText}>{data.imdbRating}</Text>

                            <Text style={orientation().titleText}>Actors</Text>
                            <Text style={orientation().subText}>{data.Actors}</Text>

                            <Text style={orientation().titleText}>Language</Text>
                            <Text style={orientation().subText}>{data.Language}</Text>

                            <Text style={orientation().titleText}>Plot</Text>
                            <Text style={orientation().subText}>{data.Plot}</Text>

                            <Text style={orientation().titleText}>Director</Text>
                            <Text style={orientation().subText}>{data.Director}</Text>

                            <Text style={orientation().titleText}>Country</Text>
                            <Text style={orientation().subText}>{data.Country}</Text>

                            <Text style={orientation().titleText}>Production</Text>
                            <Text style={orientation().subText}>{data.Production}</Text>

                            <Text style={orientation().titleText}>Released</Text>
                            <Text style={orientation().subText}>{data.Released}</Text>                   

                            <Text style={orientation().titleText}>Year</Text>
                            <Text style={orientation().subText}>{data.Year != '' ? data.Year : year}{'\n'}</Text>

                        </View>
                    </View>
                </ScrollView>
            </SafeAreaView>
            
        </>
    );
}

const styles = StyleSheet.create({

    titleText: {
        color: styleConfig.color,
        fontWeight: '600',
        fontSize: 20,
        marginVertical: 2,
    },

    subText: {
        color: styleConfig.color,
        fontWeight: '400',
        marginTop: 2,
        marginBottom: 8,
        fontSize: 18,
    },

    infoScreen: {
        paddingHorizontal: 13,
        paddingTop: 10,
        paddingBottom: 40,
        backgroundColor: styleConfig.bg
    },

    infoImageSection: {
        alignItems: 'center'
    },

    infoImage: {
        width: 180,
        height: 270,

        borderWidth: 2,
        borderColor: '#EEE'

    },
    infoScreenTextView: {
        marginTop: 30,
    },

    loading: {
        position: 'absolute',
        left: 0,
        right: 0,
        top: 0,
        bottom: 0,
        alignItems: 'center',
        justifyContent: 'center',
        backgroundColor: styleConfig.bg,
        color: styleConfig.color
    }


});


const landscape = StyleSheet.create({

    infoScreen: {
        paddingHorizontal: 16,
        paddingTop: 10,
        paddingBottom: 40,
        backgroundColor: styleConfig.bg,
        flex: 1,
        flexDirection: 'row',
    },

    infoImage: {
        width: 170,
        height: 300,
        borderWidth: 2,
        borderColor: styleConfig.color,
        marginTop: 6,
    },

    infoScreenTextView: {
        paddingLeft: 14,
        flexShrink: 1 
    },

    titleText: {
        color: styleConfig.color,
        fontWeight: '600',
        fontSize: 21,
        marginVertical: 1,
    },

    subText: {
        color: styleConfig.color,
        fontWeight: '400',
        fontSize: 19,
    },

    loading: {
        position: 'absolute',
        left: 0,
        right: 0,
        top: 0,
        bottom: 0,
        alignItems: 'center',
        justifyContent: 'center',
        backgroundColor: styleConfig.bg,
        color: styleConfig.color

    }
    

})