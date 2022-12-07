function getDatas(datas, sourceid, key, val) {
    let newdatas = datas;
    let hasName = false;
    $(newdatas).each((i, v) => {
        if (v["name"] == sourceid) {
            v[key] = val;
            hasName = true;
            return false;
        }
    })
    if (!hasName) {
        newdatas.push({
            name: sourceid
        })
        newdatas[newdatas.length - 1][key] = val
    }
    return newdatas;
}

function getDatasSourceIndex(datas, sourceid) {
    let thisIndex = -1;
    $(datas).each((i, v) => {
        if (v["name"] == sourceid) {
            thisIndex = i;
            return false;
        }
    })
    return thisIndex;
}

function getSelector(d) {
    let list = [];
    try {
        let data = JSON.parse(d);
        $(data).each((i, v) => {
            list.push(v["name"])
        })
        $(".loading-place").hide();
    }
    catch (e) {
        console.error(e);
        $(".loading-place").hide();
    }
    return list;
}

