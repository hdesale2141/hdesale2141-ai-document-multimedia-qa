const SummaryCard = ({ uploadedFile }) => {

    if (!uploadedFile) {

    return (
        <div className="bg-white p-6 rounded-xl shadow-md text-center text-gray-500">
            Upload a file to begin AI processing.
        </div>
    );
}

    return (
        <div className="bg-white p-6 rounded-xl shadow-md mt-6">

            <h2 className="text-2xl font-bold mb-4">
                File Information
            </h2>

            <p>
                <strong>File Name:</strong>
                {" "}
                {uploadedFile.fileName}
            </p>

            <p>
                <strong>File Type:</strong>
                {" "}
                {uploadedFile.fileType}
            </p>

            <div className="mt-4">

                <h3 className="font-semibold text-lg">
                    Summary
                </h3>

                <p className="mt-2 text-gray-700">
                    {
                        uploadedFile.summary ||
                        "No summary available"
                    }
                </p>

            </div>

        </div>
    );
};

export default SummaryCard;