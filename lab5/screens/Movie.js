import React, { useState, useEffect } from 'react';
import { SafeAreaView, View, VirtualizedList, StyleSheet, Text, StatusBar, Image, TextInput, TouchableOpacity, Dimensions } from 'react-native';
import { MaterialCommunityIcons } from '@expo/vector-icons';
import Swipeable from 'react-native-gesture-handler/Swipeable';
import { styleConfig } from "../style";

import * as data from '../MoviesList.json';
export const DATA = data.Search

import { choosePoster } from "../assets/global/choosePoster";
import { tt } from "../assets/global/tt";

const window = Dimensions.get("window");
const screen = Dimensions.get("screen");
const getItemCount = (data) => data.length;
const getItem = (data, index) => {
    return ({
        id: `${data[index].imdbID}`,
        title: `${data[index].Title}`,
        year: `${data[index].Year}`,
        type: `${data[index].Type}`,
        poster: `${data[index].Poster}`
    })
};


export default function Movies({ navigation }) {
    const [dimensions, setDimensions] = useState({ window, screen });
    const [selectedData, setSelectedData] = useState([]);
    const [fullData, setFullData] = useState([]);
    const [reloade, setReloade] = useState(false);
    const [search, setSearch] = useState('');

    useEffect(() => {
        setSelectedData(DATA);
        setFullData(DATA);
    }, []);

    function Item({ id, title, year, type, poster }) {
        return (
            <TouchableOpacity
                activeOpacity={0.5}
                onPress={
                    () => navigation.navigate('Details', {
                        id: id,
                        title: title,
                        year: year,
                    })}>
                <Swipeable
                    renderRightActions={LeftActions}
                    onSwipeableRightOpen={() => {
                        const onDelete = DATA.findIndex(elem => elem.imdbID === id)
                        DATA.splice(onDelete, 1);
                        searching(search)
                        setReloade(!reloade)
                    }}>
                    <View style={portrait.item}>
                        <View style={portrait.posterViev}>
                            <Image
                                style={orientation().poster}
                                source={choosePoster(poster)}
                            />
                        </View>
                        <View style={orientation().textViev}>
                            <Text style={portrait.title}>{title}</Text>
                            <Text style={portrait.details}>{year}</Text>
                            <Text style={portrait.details}>{type}</Text>
                        </View>
                    </View>
                </Swipeable>
            </TouchableOpacity>
        )
    }
    const onChange = ({ window, screen }) => {
        setDimensions({ window, screen });
    };
    const searching = (text) => {

        setSelectedData(DATA);
        setFullData(DATA);

        if (text) {
            const newData = fullData.filter(function (item) {

                const itemData = item.Title
                    ? item.Title.toUpperCase()
                    : ''.toUpperCase();
                const textData = text.toUpperCase();
                return itemData.indexOf(textData) > -1;
            });
            setSelectedData(newData);
            setSearch(text);
        } else {
            setSelectedData(fullData);
            setSearch(text);
        }
    };
    const LeftActions = () => {
        return (
            <View style={portrait.rightAction}>
                <Text style={portrait.actionText}>Delete</Text>
            </View>
        )
    }
    const orientation = () => {
        const dim = Dimensions.get('screen');
        if (dim.height >= dim.width) {
            return portrait
        } else {
            return landscape
        }
    }
    useEffect(() => {
        Dimensions.addEventListener("change", onChange);
        return () => {
            Dimensions.removeEventListener("change", onChange);
        };
    });
    
    return (
        <SafeAreaView style={portrait.container}>
            {
                React.useLayoutEffect(() => {
                    navigation.setOptions({
                        headerRight: () => (
                            <TouchableOpacity
                                activeOpacity={0.5}
                                onPress={() => { navigation.navigate('Create')}}>
                                <MaterialCommunityIcons style={portrait.addIcon} name="plus" color={'#808082'} size={30} />
                            </TouchableOpacity>
                        ),
                    });
                }, [navigation])
            }
            <View style={portrait.sectionStyle}>
                <MaterialCommunityIcons style={portrait.searchIcon} name="magnify" color={'#808082'} size={26} />
                <TextInput
                    style={portrait.textInputStyle}
                    placeholder={'Find movie you want'}
                    clearButtonMode={'while-editing'}
                    onChangeText={(text) => searching(text)}
                    value={search}
                />
            </View>

            <VirtualizedList
                data={selectedData}
                ItemSeparatorComponent={() => {return(<View style={portrait.separator}/>)}}
                renderItem={({ item }) => (
                    <Item id={item.id} title={item.title} year={item.year} type={item.type} poster={item.poster} />
                )}
                getItemCount={getItemCount}
                getItem={getItem}
            />

        </SafeAreaView>
    );
}


const portrait = StyleSheet.create({
    container: {
        flex: 1,
        marginTop: StatusBar.currentHeight,
        backgroundColor: styleConfig.bg,
    },

    separator: {
        marginLeft: 'auto',
        marginRight: 'auto',
        backgroundColor: styleConfig.separator,
        width: '92%',
        height: 0.5,
    },

    item: {
        flexDirection: 'row',
        backgroundColor: styleConfig.bg,
        height: 'auto',
        justifyContent: 'center',
        marginHorizontal: 0,
        padding: 20,
    },

    title: {
        fontSize: 30,
        color: styleConfig.color
    },

    poster: {
        width: 86,
        height: 150,
        borderRadius: 1,
        borderColor: styleConfig.color,
        borderWidth: 3,
        marginTop: 6
    },

    posterViev: {
        flex: 2
    },

    textViev: {
        flex: 10,
        marginLeft: 55,
    },


    details: {
        fontSize: 16,
        marginTop: 10,
        color: styleConfig.color
    },

    textInputStyle: {
        flex: 1,
        height: 40,
        margin: 2,
        borderRadius: 10,
        color: styleConfig.searchColor.color
        
    },

    sectionStyle: {
        flexDirection: 'row',
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: styleConfig.searchColor.bg,

        height: 40,
        borderRadius: 12,
        marginTop: 10,
        marginHorizontal: 6,
        marginBottom: 3,
    },

    searchIcon: {
        margin: 8,
        color: styleConfig.searchColor.color
    },

    rightAction: {
        flex: 1,
        justifyContent: 'center',
        backgroundColor: 'red',

    },

    actionText: {
        color: '#fff',
        padding: 20,
        textAlign: 'right'
    },

    addIcon: {
        textAlign: 'right',
        marginHorizontal: 16,
        marginBottom: 5,
        marginTop: 2,
        color: 'white'
    }
});

const landscape = StyleSheet.create({
    textViev: {
        marginRight: 20,
        flex: 10,
        marginLeft: -20
    },

    poster: {
        width: 80,
        height: 135,
        borderRadius: 1,
        marginTop: 6,
        borderColor: styleConfig.color,
        borderWidth: 3,
    },

    
})
